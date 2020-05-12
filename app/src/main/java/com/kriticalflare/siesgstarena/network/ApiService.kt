package com.kriticalflare.siesgstarena.network

import com.kriticalflare.siesgstarena.models.Contest
import com.kriticalflare.siesgstarena.models.Problem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{
    @GET("contests")
    suspend fun getContestsFromApi(): Response<List<Contest>>

    @GET("problems")
    suspend fun getProblemSetFromApi(): List<Problem>
}