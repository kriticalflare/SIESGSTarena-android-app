package com.kriticalflare.siesgstarena.di.module

import com.kriticalflare.siesgstarena.contests.repository.ContestsRepository
import com.kriticalflare.siesgstarena.problemset.repository.ProblemsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ContestsRepository(get()) }
    factory { ProblemsRepository(get()) }
}