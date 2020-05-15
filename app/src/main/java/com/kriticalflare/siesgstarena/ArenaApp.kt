package com.kriticalflare.siesgstarena

import android.app.Application
import androidx.work.*
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kriticalflare.siesgstarena.workers.ContestsWorker
import com.kriticalflare.siesgstarena.di.AppComponent
import com.kriticalflare.siesgstarena.workers.ProblemSetWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.concurrent.TimeUnit

class ArenaApp : Application(), Configuration.Provider{

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@ArenaApp)
            modules(AppComponent)
        }
        setupRecurringWork()
    }

    private fun setupRecurringWork() {
        CoroutineScope(Dispatchers.Default).launch {
            setupContestsWorker()
            setupProblemsWorker()
        }
    }

    private fun setupContestsWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val request = PeriodicWorkRequestBuilder<ContestsWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "Contests_Worker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    private fun setupProblemsWorker(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val request = PeriodicWorkRequestBuilder<ProblemSetWorker>(16, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "ProblemSet_Worker",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    override fun getWorkManagerConfiguration(): Configuration = Configuration.Builder().setMinimumLoggingLevel(android.util.Log.DEBUG).build()
}