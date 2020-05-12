package com.kriticalflare.siesgstarena.network

import com.kriticalflare.siesgstarena.BuildConfig
import retrofit2.Response
import com.kriticalflare.siesgstarena.models.Result



open class BaseApiClient {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Result<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.error("Server response error")
                }
            } else {
                Result.error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: e.toString()
            return if (BuildConfig.DEBUG) {
                Result.error("Network called failed with message $errorMessage")
            } else {
                Result.error("Check your internet connection!")
            }
        }
    }
}