package com.kriticalflare.siesgstarena.contests.repository

import com.kriticalflare.siesgstarena.database.ContestsDao
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.repository.BaseRepo

class ContestsRepository(
    private val arenaApiClient: ArenaApiClient,
    private val contestsDao: ContestsDao
) : BaseRepo(){

    fun getAllContests() = makeRequestAndSave(
        databaseQuery = {
            contestsDao.getAllContests()
        },
        networkCall = {
            arenaApiClient.getAllContests()
        },
        saveCallResult = {
            contestsDao.insertContests(it)
        }
    )
}