package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface TurnOffBulbUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class TurnOffBulbUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : TurnOffBulbUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.turnOff()
}
