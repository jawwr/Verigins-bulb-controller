package com.example.lesson1111.data.repository

import com.example.lesson1111.data.api.SampleService
import com.example.lesson1111.data.model.Joke
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

interface SampleRepository {
    suspend fun getJoke(): Result<Joke?>
    suspend fun getJokesRepositories(): Result<List<String>?>
}

class SampleRepositoryImpl @Inject constructor(
    private val service: SampleService
) : SampleRepository {
    override suspend fun getJoke(): Result<Joke?> = convertToResult { service.getRandomJoke() }

    override suspend fun getJokesRepositories(): Result<List<String>?> =
        convertToResult { service.getJokesCategories() }
}


