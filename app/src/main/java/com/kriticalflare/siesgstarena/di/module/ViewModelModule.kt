package com.kriticalflare.siesgstarena.di.module

import com.kriticalflare.siesgstarena.ac_stats.ui.StatsViewModel
import com.kriticalflare.siesgstarena.blogs.ui.BlogsViewModel
import com.kriticalflare.siesgstarena.compare.ui.CompareViewModel
import com.kriticalflare.siesgstarena.contests.ui.ContestsViewModel
import com.kriticalflare.siesgstarena.problemset.ui.ProblemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ContestsViewModel(get())
    }
    viewModel {
        ProblemsViewModel(get())
    }
    viewModel {
        BlogsViewModel(get())
    }
    viewModel {
        StatsViewModel(get())
    }
    viewModel {
        CompareViewModel(get())
    }
}
