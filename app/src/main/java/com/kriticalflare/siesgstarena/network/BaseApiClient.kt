package com.kriticalflare.siesgstarena.network

import com.kriticalflare.siesgstarena.BuildConfig
import com.kriticalflare.siesgstarena.models.Resource
import org.koin.core.KoinComponent
import org.koin.core.get
import retrofit2.Response

open class BaseApiClient : KoinComponent {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Resource<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.success(body)
                } else {
                    Resource.error("Server response error")
                }
            } else {
                Resource.error("${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: e.toString()
            return if (BuildConfig.DEBUG) {
                Resource.error("Network called failed with message $errorMessage")
            } else {
                Resource.error("Check your internet connection!")
            }
        }
    }
}
