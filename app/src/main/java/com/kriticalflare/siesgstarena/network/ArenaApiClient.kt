package com.kriticalflare.siesgstarena.network

class ArenaApiClient(private val apiService: ApiService) : BaseApiClient() {
    suspend fun getAllContests() = getResult {
        apiService.getContestsFromApi()
    }

    suspend fun getAllProblemSet() = getResult {
        apiService.getProblemSetFromApi()
    }

    suspend fun getAllBlogs() = getResult {
        apiService.getBlogsFromApi()
    }

    suspend fun getAllTopStats() = getResult {
        apiService.getTopAcStats()
    }

    suspend fun getComparison(username1: String, username2: String) = getResult {
        apiService.getComparison(username1, username2)
    }
}
