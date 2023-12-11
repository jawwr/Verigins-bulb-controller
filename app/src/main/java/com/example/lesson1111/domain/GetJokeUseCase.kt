package com.example.lesson1111.domain

import com.example.lesson1111.data.model.Joke
import com.example.lesson1111.data.repository.SampleRepository
import javax.inject.Inject

interface GetJokeUseCase {
    suspend operator fun invoke(): Result<Joke?>
}

class GetJokeUseCaseImpl @Inject constructor(
    private val repository: SampleRepository
) : GetJokeUseCase {
    override suspend fun invoke(): Result<Joke?> = repository.getJoke()
}
