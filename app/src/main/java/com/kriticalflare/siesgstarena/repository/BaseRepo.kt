package com.kriticalflare.siesgstarena.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.kriticalflare.siesgstarena.models.Resource
import kotlinx.coroutines.Dispatchers

open class BaseRepo {

    protected fun <T> makeRequest(request: suspend () -> Resource<T>) = liveData {
        emit(Resource.loading())

        val response = request.invoke()

        when (response.status) {
            Resource.Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }
            Resource.Status.ERROR -> {
                emit(Resource.error(response.message!!))
            }
            else -> { }
        }
    }

    protected fun <T, A> makeRequestAndSave(
        databaseQuery: () -> LiveData<T>,
        networkCall: suspend () -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val source = databaseQuery.invoke().map {
            Log.d("SIESDB","db ${it.toString()}")
            Resource.success(it) }
        emitSource(source)

        val response = networkCall.invoke()
        when (response.status) {
            Resource.Status.SUCCESS -> {
                Log.d("Response",response.data.toString())
                saveCallResult(response.data!!)
            }
            Resource.Status.ERROR -> {
                emit(Resource.error(response.message!!))
                emitSource(source)
            }
            else -> {}
        }
    }
}