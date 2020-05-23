package com.kriticalflare.siesgstarena.blogs.repository

import com.kriticalflare.siesgstarena.database.BlogsDao
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.repository.BaseRepo

class BlogsRepository(
    private val apiClient: ArenaApiClient,
    private val blogsDao: BlogsDao
) : BaseRepo() {

    fun getAllBlogs() = makeRequestAndSave(
        databaseQuery = {
            blogsDao.getAllBlogs()
        },
        networkCall = {
            apiClient.getAllBlogs()
        },
        saveCallResult = {
            blogsDao.insertBlogs(it)
        }
    )

    suspend fun refreshBlogs() = refreshAndSave(
        networkCall = {
            apiClient.getAllBlogs()
        },
        saveCallResult = {
            blogsDao.insertBlogs(it)
        }
    )
}