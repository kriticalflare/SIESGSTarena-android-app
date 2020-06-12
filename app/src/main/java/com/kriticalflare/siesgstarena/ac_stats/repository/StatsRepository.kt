package com.kriticalflare.siesgstarena.ac_stats.repository

import com.kriticalflare.siesgstarena.database.StatsDao
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.repository.BaseRepo

class StatsRepository(
    private val apiClient: ArenaApiClient,
    private val statsDao: StatsDao
) : BaseRepo() {

    fun getAllStats() = makeRequestAndSave(
        databaseQuery = {
            statsDao.getAllStats()
        },
        networkCall = {
            apiClient.getAllTopStats()
        },
        saveCallResult = {
            statsDao.insertStats(it)
        }
    )

    suspend fun refreshBlogs() = refreshAndSave(
        networkCall = {
            apiClient.getAllTopStats()
        },
        saveCallResult = {
            statsDao.insertStats(it)
        }
    )
}
