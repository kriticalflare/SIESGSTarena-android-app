package com.kriticalflare.siesgstarena

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kriticalflare.siesgstarena.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ArenaApp: Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@ArenaApp)
            modules(AppComponent)
        }
    }
}