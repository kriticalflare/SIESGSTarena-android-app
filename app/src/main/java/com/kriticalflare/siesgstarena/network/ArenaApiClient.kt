package com.kriticalflare.siesgstarena.network

class ArenaApiClient(private val apiService: ApiService): BaseApiClient(){
    suspend fun getAllContests() = getResult {
        apiService.getContestsFromApi()
    }

    suspend fun getAllProblemSet() = getResult {
        apiService.getProblemSetFromApi()
    }
}