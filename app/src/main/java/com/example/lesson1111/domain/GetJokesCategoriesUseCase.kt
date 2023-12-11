package com.example.lesson1111.domain

import com.example.lesson1111.data.repository.SampleRepository
import javax.inject.Inject

interface GetJokesCategoriesUseCase {
    suspend operator fun invoke(): Result<List<String>?>
}

class GetJokesCategoriesUseCaseImpl @Inject constructor(
    private val repository: SampleRepository
) : GetJokesCategoriesUseCase {
    override suspend fun invoke(): Result<List<String>?> = repository.getJokesRepositories()
}
