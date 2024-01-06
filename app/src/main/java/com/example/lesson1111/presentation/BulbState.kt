package com.example.lesson1111.presentation

import com.example.lesson1111.UiState
import com.example.lesson1111.data.model.BulbColor

data class BulbState(
    var bulbState: UiState<Boolean> = UiState.Loading,
    var brightness: UiState<Int> = UiState.Loading,
    var colors: UiState<List<BulbColor>> = UiState.Loading,
    val currentColor: UiState<String> = UiState.Loading
) {
    fun isSuccess() = bulbState is UiState.Success
            && brightness is UiState.Success
            && colors is UiState.Success
            && currentColor is UiState.Success
}