package com.kriticalflare.siesgstarena.contests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.contests.repository.ContestsRepository
import kotlinx.coroutines.launch
import com.kriticalflare.siesgstarena.models.Result

class ContestsViewModel(
    private val contestsRepository: ContestsRepository
) : ViewModel() {
    fun getAllContests() = contestsRepository.getAllContests()
}