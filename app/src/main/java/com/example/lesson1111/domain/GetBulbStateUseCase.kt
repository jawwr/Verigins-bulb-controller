package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface GetBulbStateUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class GetBulbStateUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : GetBulbStateUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.getState()
}
