package com.kriticalflare.siesgstarena.contests.ui

import androidx.lifecycle.ViewModel
import com.kriticalflare.siesgstarena.contests.repository.ContestsRepository

class ContestsViewModel(
    private val contestsRepository: ContestsRepository
) : ViewModel() {
    fun getAllContests() = contestsRepository.getAllContests()
}