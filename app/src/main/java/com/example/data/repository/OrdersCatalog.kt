package com.example.data.repository

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.example.data.models.ChurchOffice
import com.example.data.models.LocalizedText
import com.example.data.models.ReligiousOrder
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.InputStream

internal data class RawOrder(
    val id: String? = null, val name: RawLT? = null, val abbreviation: String? = null,
    val foundationYear: String? = null, val founder: RawLT? = null,
    val description: RawLT? = null, val charism: RawLT? = null, val gender: String? = null
)
internal data class RawOrderDetails(
    val id: String? = null, val kind: String? = null, val motto: RawLT? = null, val approval: RawLT? = null,
    val notableSaints: RawLTList? = null, val presence: RawLT? = null, val sources: List<String>? = null
)
internal data class RawOffice(
    val id: String? = null, val name: RawLT? = null, val description: RawLT? = null,
    val group: String? = null, val after: String? = null, val sources: List<String>? = null
)

/**
 * Merges the optional JSON files in the `orders` asset folder over [ReligiousOrdersData].
 * Missing or malformed files are ignored, so the base data always shows.
 */
class OrdersCatalog(private val open: (String) -> InputStream) {
    companion object {
        const val NEW_ORDERS = "orders-new.json"
        const val DETAILS = "orders-details.json"
        const val NEW_OFFICES = "offices-new.json"

        @Volatile private var assets: AssetManager? = null
        /** Test hook: replaces [default] while set. */
        @Volatile internal var override: OrdersCatalog? = null
        fun install(context: Context) { assets = context.applicationContext.assets }
        private val bundled by lazy {
            OrdersCatalog { file ->
                assets?.open("orders/$file")
                    ?: OrdersCatalog::class.java.classLoader!!.getResourceAsStream(file)
                    ?: error("Orders asset unavailable")
            }
        }
        val default: OrdersCatalog get() = override ?: bundled
    }

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private inline fun <reified T> read(name: String): List<T>? = try {
        val adapter = moshi.adapter<List<T>>(Types.newParameterizedType(List::class.java, T::class.java))
        open(name).bufferedReader().use { adapter.fromJson(it.readText()) }
    } catch (e: Exception) {
        if (e !is java.io.FileNotFoundException && e.message?.contains("unavailable") != true)
            runCatching { Log.w("OrdersCatalog", "Ignoring $name: ${e.message}") }
        null
    }

    private fun RawLT?.lt() = LocalizedText(this?.es.orEmpty(), this?.en.orEmpty(), this?.pt.orEmpty())
    private fun RawLT?.ltOrNull() = lt().takeIf { it.es.isNotBlank() || it.en.isNotBlank() || it.pt.isNotBlank() }
    private fun List<String>?.clean() = orEmpty().filter { it.isNotBlank() }.map { it.trim() }

    private val details: Map<String, RawOrderDetails> by lazy {
        read<RawOrderDetails>(DETAILS).orEmpty().filterNotNull()
            .mapNotNull { d -> d.id?.takeIf { it.isNotBlank() }?.let { it to d } }.toMap()
    }

    private fun ReligiousOrder.withDetails(): ReligiousOrder {
        val d = details[id] ?: return this
        val n = d.notableSaints
        val saints = (0 until maxOf(n?.es?.size ?: 0, n?.en?.size ?: 0, n?.pt?.size ?: 0)).map { i ->
            LocalizedText(n?.es?.getOrNull(i).orEmpty(), n?.en?.getOrNull(i).orEmpty(), n?.pt?.getOrNull(i).orEmpty())
        }.filter { it.es.isNotBlank() || it.en.isNotBlank() || it.pt.isNotBlank() }
        return copy(
            kind = d.kind?.takeIf { it.isNotBlank() },
            motto = d.motto.ltOrNull(),
            approval = d.approval.ltOrNull(),
            notableSaints = saints,
            presence = d.presence.ltOrNull(),
            sources = d.sources.clean()
        )
    }

    val orders: List<ReligiousOrder> by lazy {
        val base = ReligiousOrdersData.orders
        val seen = base.map { it.id }.toMutableSet()
        val added = read<RawOrder>(NEW_ORDERS).orEmpty().filterNotNull().mapNotNull { r ->
            val id = r.id?.takeIf { it.isNotBlank() && seen.add(it) } ?: return@mapNotNull null
            val name = r.name.lt()
            if (name.es.isBlank() && name.en.isBlank() && name.pt.isBlank()) return@mapNotNull null
            ReligiousOrder(
                id, name, r.abbreviation.orEmpty(), r.foundationYear.orEmpty(), r.founder.lt(),
                r.description.lt(), r.charism.lt(), gender = r.gender?.takeIf { it.isNotBlank() }
            )
        }
        (base + added).map { it.withDetails() }
    }

    val offices: List<ChurchOffice> by lazy {
        val out = ReligiousOrdersData.offices.toMutableList()
        val seen = out.map { it.id }.toMutableSet()
        for (r in read<RawOffice>(NEW_OFFICES).orEmpty().filterNotNull()) {
            val id = r.id?.takeIf { it.isNotBlank() && seen.add(it) } ?: continue
            val office = ChurchOffice(
                id, r.name.lt(), r.description.lt(), r.sources.clean(), r.group?.takeIf { it.isNotBlank() }
            )
            val at = r.after?.let { a -> out.indexOfFirst { it.id == a } } ?: -1
            if (at >= 0) out.add(at + 1, office) else out.add(office)
        }
        out
    }
}
