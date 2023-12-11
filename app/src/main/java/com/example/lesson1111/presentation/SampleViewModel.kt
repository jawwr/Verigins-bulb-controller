package com.example.lesson1111.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson1111.UiState
import com.example.lesson1111.domain.GetBulbStateUseCase
import com.example.lesson1111.domain.GetJokesCategoriesUseCase
import com.example.lesson1111.domain.TurnOffBulbUseCase
import com.example.lesson1111.domain.TurnOnBulbUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SampleViewModel @Inject constructor(
    private val getJokesCategoriesUseCase: GetJokesCategoriesUseCase,
    private val getBulbStateUseCase: GetBulbStateUseCase,
    private val turnOnBulbUseCase: TurnOnBulbUseCase,
    private val turnOffBulbUseCase: TurnOffBulbUseCase
) : ViewModel() {
    private val _jokesCategories = MutableLiveData<UiState<Boolean>>(UiState.Loading)
    val jokesCategories: LiveData<UiState<Boolean>>
        get() = _jokesCategories

//    fun getJokesCategories() {
//        viewModelScope.launch {
//            _jokesCategories post getJokesCategoriesUseCase()
//        }
//    }

    fun getBulbState() {
        viewModelScope.launch {
            _jokesCategories post getBulbStateUseCase()
        }
    }

    fun turnBulbOn() {
        viewModelScope.launch {
            _jokesCategories post turnOnBulbUseCase()
        }
    }

    fun turnBulbOff() {
        viewModelScope.launch {
            _jokesCategories post turnOffBulbUseCase()
        }
    }

    private infix fun <T> MutableLiveData<UiState<T>>.post(value: Result<T?>) {
        this.postValue(
            if (value.isSuccess) {
                value.getOrNull()?.let { UiState.Success(it) } ?: UiState.Failure("Data was null")
            } else {
                UiState.Failure(value.exceptionOrNull()?.message ?: "")
            }
        )
    }
}
