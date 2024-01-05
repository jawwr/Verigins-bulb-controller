package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface UpdateBulbColorUseCase {
    suspend operator fun invoke(color: String): Result<Boolean?>
}

class UpdateBulbColorUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : UpdateBulbColorUseCase {
    override suspend fun invoke(color: String): Result<Boolean?> = repository.setColor(color)
}