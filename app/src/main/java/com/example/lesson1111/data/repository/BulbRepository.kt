package com.example.lesson1111.data.repository

import com.example.lesson1111.data.api.BulbService
import com.example.lesson1111.data.model.BulbColor
import javax.inject.Inject

interface BulbRepository {
    suspend fun getState(): Result<Boolean>
    suspend fun turnOn(): Result<Boolean>
    suspend fun turnOff(): Result<Boolean>
    suspend fun getColors(): Result<List<BulbColor>>
    suspend fun setColor(color: String): Result<Boolean>
    suspend fun getColorNames(): Result<List<String>>
    suspend fun getCurrentColor(): Result<BulbColor>
    suspend fun getCurrentBrightness(): Result<Int>
    suspend fun setBrightnessLevel(level: Int): Result<Boolean>
}

class BulbRepositoryImpl @Inject constructor(
    private val service: BulbService
) : BulbRepository {
    override suspend fun getState(): Result<Boolean> =
        convertToResult { service.getState() }

    override suspend fun turnOn(): Result<Boolean> =
        convertToResult { service.turnOn() }

    override suspend fun turnOff(): Result<Boolean> =
        convertToResult { service.turnOff() }

    override suspend fun getColors(): Result<List<BulbColor>> =
        convertToResult { service.getColors() }

    override suspend fun setColor(color: String): Result<Boolean> =
        convertToResult { service.setColor(color) }

    override suspend fun getColorNames(): Result<List<String>> =
        convertToResult { service.getColorNames() }

    override suspend fun getCurrentColor(): Result<BulbColor> =
        convertToResult { service.getCurrentColor() }

    override suspend fun getCurrentBrightness(): Result<Int> =
        convertToResult { service.getCurrentBrightness() }

    override suspend fun setBrightnessLevel(level: Int): Result<Boolean> =
        convertToResult { service.setBrightnessLevel(level) }
}
