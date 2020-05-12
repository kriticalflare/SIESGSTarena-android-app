package com.kriticalflare.siesgstarena.di.module

import com.kriticalflare.siesgstarena.network.ApiService
import com.kriticalflare.siesgstarena.network.ArenaApiClient
import com.kriticalflare.siesgstarena.network.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module{
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {  level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(RequestInterceptor())
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl("http://arena.siesgst.ac.in/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    factory {
        get<Retrofit>().create(ApiService::class.java)
    }

    factory { ArenaApiClient(get()) }
}