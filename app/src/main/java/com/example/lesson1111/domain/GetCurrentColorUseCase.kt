package com.example.lesson1111.domain

import com.example.lesson1111.data.model.BulbColor
import com.example.lesson1111.data.repository.BulbRepository
import javax.inject.Inject

interface GetCurrentColorUseCase {
    suspend operator fun invoke(): Result<BulbColor>
}

class GetCurrentColorUseCaseImpl @Inject constructor(
    private val repository: BulbRepository
) : GetCurrentColorUseCase {
    override suspend fun invoke(): Result<BulbColor> = repository.getCurrentColor()
}