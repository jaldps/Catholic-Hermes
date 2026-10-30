package com.example.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Reads the device's last known position through the platform
 * [LocationManager].
 *
 * Play Services' fused provider is not used so the feature also works on
 * devices without Google Play, and so the app needs no extra dependency for it.
 * Only the last known fix is read — never a continuous stream — because a
 * parish search does not justify tracking anyone.
 */
class DeviceLocationProvider(private val context: Context) {

    companion object {
        val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun hasPermission(): Boolean = PERMISSIONS.any {
        ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * The most recent fix any enabled provider holds, or null when permission
     * is absent, location is switched off, or nothing has a fix yet.
     */
    @SuppressLint("MissingPermission")
    suspend fun lastKnownLocation(): Location? = withContext(Dispatchers.IO) {
        if (!hasPermission()) return@withContext null
        val manager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
            ?: return@withContext null

        val providers = runCatching { manager.getProviders(true) }.getOrNull().orEmpty()
        providers
            .mapNotNull { provider ->
                runCatching { manager.getLastKnownLocation(provider) }.getOrNull()
            }
            // Prefer the freshest fix; accuracy varies too much between
            // providers to rank on it.
            .maxByOrNull { it.time }
    }
}
