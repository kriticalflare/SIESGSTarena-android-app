package com.kriticalflare.siesgstarena.contests.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.contests.repository.ContestsRepository
import kotlinx.coroutines.launch

class ContestsViewModel(
    private val contestsRepository: ContestsRepository
) : ViewModel() {
    private var _contestList = MutableLiveData<List<Contest>>()
    val contestsList: LiveData<List<Contest>> = _contestList

    init {
        viewModelScope.launch {
            _contestList.postValue( contestsRepository.fetchContests().asReversed())
        }
    }
}