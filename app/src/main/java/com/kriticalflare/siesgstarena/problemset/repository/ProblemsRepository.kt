package com.kriticalflare.siesgstarena.problemset.repository

import com.kriticalflare.siesgstarena.database.ProblemsDao
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.repository.BaseRepo

class ProblemsRepository(
    private val apiClient: ArenaApiClient,
    private val problemsDao: ProblemsDao
) : BaseRepo() {

    fun getAllProblemSets() = makeRequestAndSave(
        databaseQuery = {
            problemsDao.getAllProblem()
        },
        networkCall = {
            apiClient.getAllProblemSet()
        },
        saveCallResult = {
            problemsDao.insertProblems(it)
        }
    )

    suspend fun refreshProblemSet() = refreshAndSave(
        networkCall = {
            apiClient.getAllProblemSet()
        },
        saveCallResult = {
            problemsDao.insertProblems(it)
        }
    )
}