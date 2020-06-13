package com.kriticalflare.siesgstarena.compare.ui

import androidx.lifecycle.ViewModel
import com.kriticalflare.siesgstarena.compare.repo.ComparisonRepository

class CompareViewModel(private val comparisonRepository: ComparisonRepository) : ViewModel() {

    fun getComparison(username1: String, username2: String) = comparisonRepository.getComparison(username1, username2)
}
