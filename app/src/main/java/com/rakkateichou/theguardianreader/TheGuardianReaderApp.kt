package com.rakkateichou.theguardianreader

import android.app.Application
import com.rakkateichou.theguardianreader.di.DaggerApplicationComponent
import timber.log.Timber

class TheGuardianReaderApp : Application() {
    val appComponent = DaggerApplicationComponent.create()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}