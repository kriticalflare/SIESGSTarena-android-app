package com.kriticalflare.siesgstarena.di.module

import androidx.room.Room
import com.kriticalflare.siesgstarena.database.ArenaDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module{
    single {
       Room.databaseBuilder(androidApplication(),ArenaDatabase::class.java, "arena")
           .fallbackToDestructiveMigration()
           .build()
    }

    single{
        get<ArenaDatabase>().contestDao()
    }
}