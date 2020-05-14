package com.kriticalflare.siesgstarena.problemset.ui

import androidx.lifecycle.ViewModel
import com.kriticalflare.siesgstarena.problemset.repository.ProblemsRepository


class ProblemsViewModel(
    private val problemsRepository: ProblemsRepository
) : ViewModel() {

    fun getAllProblemSets() = problemsRepository.getAllProblemSets()
}