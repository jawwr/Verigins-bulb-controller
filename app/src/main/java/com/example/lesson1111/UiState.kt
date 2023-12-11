package com.example.lesson1111

sealed class UiState<out T> {
    class Success<T>(val value: T) : UiState<T>()
    class Failure(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}
