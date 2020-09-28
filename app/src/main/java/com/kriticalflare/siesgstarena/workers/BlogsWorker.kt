package com.kriticalflare.siesgstarena.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.kriticalflare.siesgstarena.database.BlogsDao
import com.kriticalflare.siesgstarena.models.Resource
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import kotlinx.coroutines.coroutineScope
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@OptIn(KoinApiExtension::class)
class BlogsWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params),
    KoinComponent {
    override suspend fun doWork(): Result = coroutineScope {
        val apiClient: ArenaApiClient = get()
        val blogsDao: BlogsDao = get()

        Log.d(javaClass.simpleName, "Worker Started!")

        try {
            val result = apiClient.getAllBlogs()
            Log.d(javaClass.simpleName, "$result")
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    Log.d(javaClass.simpleName, "success ${result.data}")
                    result.data?.let { blogsDao.insertBlogs(it) }
                    Result.success()
                }
                Resource.Status.ERROR -> {
                    Log.d(javaClass.simpleName, "error ${result.message}")
                    Result.retry()
                }
                else -> {
                    Log.d(javaClass.simpleName, "else ${result.status}")
                    Result.retry()
                }
            }
        } catch (e: Throwable) {
            Log.d("ContestsWorker", "Failed to sync blogs data ${e.message}")
            Result.retry()
        }
    }
}
