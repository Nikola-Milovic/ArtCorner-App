package com.nikolam.artcorner

import android.app.Application
import com.arkivanov.mvikotlin.timetravel.server.TimeTravelServer
import com.nikolam.artcorner.di.appModule
import nikolam.artcorner.common.data.dataModule
import nikolam.artcorner.common.di.commonModule
import nikolam.artcorner.common.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val loggingEnabled = true
        // KOIN - DI
        initKoin(loggingEnabled) {
            androidLogger(Level.NONE) // No virtual method elapsedNow
            androidContext(this@App)
            modules(appModule(loggingEnabled), commonModule(true), dataModule())
        }

        TimeTravelServer().start()
    }
}
