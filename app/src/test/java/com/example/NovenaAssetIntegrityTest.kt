package com.example

import com.squareup.moshi.Moshi
import org.junit.Assert.*
import org.junit.Test
import java.security.MessageDigest

/** Independent runtime equivalence established this immutable body baseline before migration. */
class NovenaAssetIntegrityTest {
    @Test fun everyPrayerBodyStillMatchesThePreservedCorpus() {
        val loader=javaClass.classLoader!!
        val adapter=Moshi.Builder().build().adapter(List::class.java)
        val entries=loader.getResourceAsStream("novena-body-hashes.json")!!.bufferedReader().use {adapter.fromJson(it.readText())}!!
        assertEquals(246,entries.size)
        for(row in entries) {
            val map=row as Map<*,*>
            val name=map["file"] as String
            val bytes=loader.getResourceAsStream(name)!!.use {it.readBytes()}
            val actual=MessageDigest.getInstance("SHA-256").digest(bytes).joinToString("") {"%02x".format(it)}
            assertEquals(name,map["sha256"],actual)
        }
    }
}
