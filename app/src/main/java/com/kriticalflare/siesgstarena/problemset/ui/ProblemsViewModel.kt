package com.kriticalflare.siesgstarena.problemset.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.problemset.repository.ProblemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProblemsViewModel(
    private val problemsRepository: ProblemsRepository
) : ViewModel() {

    fun getAllProblemSets() = problemsRepository.getAllProblemSets()

    fun refreshProblemSet() {
        viewModelScope.launch(Dispatchers.IO) {
            problemsRepository.refreshProblemSet()
        }
    }
}
