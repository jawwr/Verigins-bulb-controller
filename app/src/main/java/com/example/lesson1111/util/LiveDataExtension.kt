package com.example.lesson1111.util

import androidx.lifecycle.MutableLiveData
import com.example.lesson1111.UiState

infix fun <T> MutableLiveData<UiState<T>>.post(value: T?) =
    this.postValue(value?.let { UiState.Success(value) }
        ?: UiState.Failure("Что-то пошло не так!1!"))