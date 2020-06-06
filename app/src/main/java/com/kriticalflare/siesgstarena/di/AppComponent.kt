package com.kriticalflare.siesgstarena.di

import com.kriticalflare.siesgstarena.di.module.apiModule
import com.kriticalflare.siesgstarena.di.module.repositoryModule
import com.kriticalflare.siesgstarena.di.module.roomModule
import com.kriticalflare.siesgstarena.di.module.sharedPrefModule
import com.kriticalflare.siesgstarena.di.module.viewModelModule

val AppComponent = listOf(
    apiModule,
    viewModelModule,
    repositoryModule,
    roomModule,
    sharedPrefModule
)
