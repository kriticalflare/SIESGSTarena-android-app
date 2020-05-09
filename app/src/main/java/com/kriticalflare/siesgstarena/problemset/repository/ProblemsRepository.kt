package com.kriticalflare.siesgstarena.problemset.repository

import com.kriticalflare.siesgstarena.models.Problem
import com.kriticalflare.siesgstarena.network.ApiService

class ProblemsRepository(
    private val apiService: ApiService
) {

    suspend fun fetchProblemSet(): List<Problem>{
        return apiService.getProblemSetFromApi()
    }
}