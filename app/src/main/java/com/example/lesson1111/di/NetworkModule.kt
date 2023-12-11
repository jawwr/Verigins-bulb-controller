package com.example.lesson1111.di

import com.example.lesson1111.data.api.BulbService
import com.example.lesson1111.data.api.SampleService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
class NetworkModule {
    @Provides
    fun provideSampleService(): SampleService = Retrofit.Builder()
        .baseUrl("https://api.chucknorris.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SampleService::class.java)

    @Provides
    fun provideBulbService(): BulbService = Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build())
        .baseUrl("http://195.54.14.121:8086/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BulbService::class.java)
}
