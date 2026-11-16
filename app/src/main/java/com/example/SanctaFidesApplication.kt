package com.example

import android.app.Application
import com.example.data.repository.NovenaCatalog
import com.example.data.repository.PrayerDetailsCatalog

class SanctaFidesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NovenaCatalog.install(this)
        PrayerDetailsCatalog.install(this)
        com.example.data.repository.QuestionDeeperCatalog.install(this)
        com.example.data.repository.SaintsCatalog.install(this)
        com.example.data.repository.OrdersCatalog.install(this)
    }
}
