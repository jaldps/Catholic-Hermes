package com.example

import android.app.Application
import com.example.data.repository.NovenaCatalog

class SanctaFidesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NovenaCatalog.install(this)
    }
}
