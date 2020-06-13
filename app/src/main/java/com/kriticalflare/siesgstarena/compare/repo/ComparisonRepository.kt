package com.kriticalflare.siesgstarena.compare.repo

import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.repository.BaseRepo

class ComparisonRepository(private val apiClient: ArenaApiClient) : BaseRepo() {

    fun getComparison(username1: String, username2: String) = makeRequest {
        apiClient.getComparison(username1, username2)
    }
}
