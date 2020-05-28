package com.kriticalflare.siesgstarena.contests.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.contests.repository.ContestsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContestsViewModel(
    private val contestsRepository: ContestsRepository
) : ViewModel() {
    fun getAllContests() = contestsRepository.getAllContests()

    fun refreshContests() {
        viewModelScope.launch(Dispatchers.IO) {
            contestsRepository.refreshContests()
        }
    }
}
