package com.kriticalflare.siesgstarena.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.kriticalflare.siesgstarena.models.Result
import kotlinx.coroutines.Dispatchers

open class BaseRepo {

    protected fun <T> makeRequest(request: suspend () -> Result<T>) = liveData {
        emit(Result.loading())

        val response = request.invoke()

        when (response.status) {
            Result.Status.SUCCESS -> {
                emit(Result.success(response.data))
            }
            Result.Status.ERROR -> {
                emit(Result.error(response.message!!))
            }
            else -> { }
        }
    }

    protected fun <T, A> makeRequestAndSave(
        databaseQuery: () -> LiveData<T>,
        networkCall: suspend () -> Result<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Result<T>> = liveData(Dispatchers.IO) {
        emit(Result.loading())

        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val response = networkCall.invoke()
        when (response.status) {
            Result.Status.SUCCESS -> {
                Log.d("Response",response.data.toString())
                saveCallResult(response.data!!)
            }
            Result.Status.ERROR -> {
                emit(Result.error(response.message!!))
                emitSource(source)
            }
            else -> {}
        }
    }
}