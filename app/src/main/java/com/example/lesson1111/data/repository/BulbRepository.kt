package com.example.lesson1111.data.repository

import com.example.lesson1111.data.api.BulbService
import javax.inject.Inject

interface BulbRepository {
    suspend fun getState(): Result<Boolean?>
    suspend fun turnOn(): Result<Boolean?>
    suspend fun turnOff(): Result<Boolean?>
}

class BulbRepositoryImpl @Inject constructor(
    private val service: BulbService
) : BulbRepository {
    override suspend fun getState(): Result<Boolean?> =
        convertToResult { service.getState() }

    override suspend fun turnOn(): Result<Boolean?> =
        convertToResult { service.turnOn() }

    override suspend fun turnOff(): Result<Boolean?> =
        convertToResult { service.turnOff() }
}
