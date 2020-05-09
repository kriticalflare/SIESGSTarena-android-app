package com.kriticalflare.siesgstarena.contests.repository

import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.network.ApiService

class ContestsRepository(
    private val apiService: ApiService
) {

    suspend fun fetchContests(): List<Contest>{
        return apiService.getContestsFromApi()
    }
}