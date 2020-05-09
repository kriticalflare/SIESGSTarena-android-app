package com.kriticalflare.siesgstarena.problemset.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kriticalflare.siesgstarena.models.Problem
import com.kriticalflare.siesgstarena.problemset.repository.ProblemsRepository
import kotlinx.coroutines.launch

class ProblemsViewModel(
    private val problemsRepository: ProblemsRepository
) : ViewModel() {
    private var _problemSetList = MutableLiveData<List<Problem>>()
    val problemSetList: LiveData<List<Problem>> = _problemSetList

    init {
        viewModelScope.launch {
            _problemSetList.postValue( problemsRepository.fetchProblemSet().asReversed())
        }
    }
}