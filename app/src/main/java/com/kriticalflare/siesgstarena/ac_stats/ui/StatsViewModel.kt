package com.kriticalflare.siesgstarena.ac_stats.ui

import androidx.lifecycle.ViewModel
import com.kriticalflare.siesgstarena.ac_stats.repository.StatsRepository

class StatsViewModel(private val statsRepository: StatsRepository) : ViewModel() {
    fun getAllStats() = statsRepository.getAllStats()
}
