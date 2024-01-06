package com.example.lesson1111.data.repository

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T> convertToResult(func: suspend () -> Response<T>): Result<T> {
    runCatching { func() }.fold(
        onSuccess = {
            return if (it.isSuccessful) {
                Result.success(requireNotNull(it.body()))
            } else {
                Result.failure(HttpException(it))
            }
        },
        onFailure = {
            return Result.failure(it)
        },
    )
}