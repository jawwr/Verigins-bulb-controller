package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface GetBrightnessUseCase {
    suspend operator fun invoke(): Result<Int>
}

class GetBrightnessUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : GetBrightnessUseCase {
    override suspend fun invoke(): Result<Int> = repository.getCurrentBrightness()
}