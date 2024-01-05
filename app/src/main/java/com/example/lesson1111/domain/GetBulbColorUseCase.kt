package com.example.lesson1111.domain

import com.example.lesson1111.data.model.BulbColor
import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface GetBulbColorUseCase {
    suspend operator fun invoke(): Result<List<BulbColor>?>
}

class GetBulbColorUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : GetBulbColorUseCase {
    override suspend fun invoke(): Result<List<BulbColor>?> = repository.getColors()
}