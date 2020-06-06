package com.kriticalflare.siesgstarena.di.module

import androidx.preference.PreferenceManager
import com.kriticalflare.siesgstarena.authentication.AuthenticationManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPrefModule = module {
    single {
        PreferenceManager.getDefaultSharedPreferences(androidContext())
    }
    single {
        AuthenticationManager(get())
    }
}
