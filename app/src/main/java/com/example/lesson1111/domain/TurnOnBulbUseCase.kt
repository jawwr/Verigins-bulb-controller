package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface TurnOnBulbUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class TurnOnBulbUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : TurnOnBulbUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.turnOn()
}
