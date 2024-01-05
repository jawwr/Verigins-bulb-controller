package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface SetBrightnessUseCase {
    suspend operator fun invoke(level: Int): Result<Boolean>
}

class SetBrightnessUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : SetBrightnessUseCase {
    override suspend fun invoke(level: Int): Result<Boolean> = repository.setBrightnessLevel(level)
}