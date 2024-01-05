package com.example.lesson1111.data.api

import com.example.lesson1111.data.model.BulbColor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BulbService {
    @POST("state/on")
    suspend fun turnOn(): Response<Boolean>

    @POST("state/off")
    suspend fun turnOff(): Response<Boolean>

    @GET("state/")
    suspend fun getState(): Response<Boolean>

    @GET("color/")
    suspend fun getColors(): Response<List<BulbColor>>

    @POST("color/")
    suspend fun setColor(@Query("color") color: String): Response<Boolean>

    @GET("color/names_only")
    suspend fun getColorNames(): Response<List<String>>

    @GET("color/current")
    suspend fun getCurrentColor(): Response<BulbColor>

    @GET("brightness/current")
    suspend fun getCurrentBrightness(): Response<Int>

    @POST("brightness/")
    suspend fun setBrightnessLevel(@Query("level") level: Int): Response<Boolean>
}
