package com.example.lesson1111.util

import androidx.lifecycle.MutableLiveData
import com.example.lesson1111.UiState

infix fun <T> MutableLiveData<UiState<T>>.post(value: Result<T?>) {
    this.postValue(
        if (value.isSuccess) {
            value.getOrNull()?.let { UiState.Success(it) } ?: UiState.Failure("Data was null")
        } else {
            UiState.Failure(value.exceptionOrNull()?.message ?: "")
        }
    )
}