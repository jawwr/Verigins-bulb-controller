package com.example.lesson1111.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface BulbService {
    @POST("state/on")
    suspend fun turnOn(): Response<Boolean>

    @POST("state/off")
    suspend fun turnOff(): Response<Boolean>

    @GET("state/")
    suspend fun getState(): Response<Boolean>
}
