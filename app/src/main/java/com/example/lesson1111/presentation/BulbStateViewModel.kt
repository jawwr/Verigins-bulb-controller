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
import com.example.lesson1111.util.post
import kotlinx.coroutines.launch
import javax.inject.Inject

class BulbStateViewModel @Inject constructor(
    private val getBulbStateUseCase: GetBulbStateUseCase,
    private val turnOnBulbUseCase: TurnOnBulbUseCase,
    private val turnOffBulbUseCase: TurnOffBulbUseCase
) : ViewModel() {
    private val _bulbStates = MutableLiveData<UiState<Boolean>>(UiState.Loading)
    val bulbStates: LiveData<UiState<Boolean>>
        get() = _bulbStates

    fun getBulbState() {
        viewModelScope.launch {
            val currentState = getBulbStateUseCase()
            _bulbStates post currentState
        }
    }

    fun turnBulbOn() {
        viewModelScope.launch {
            val isOn = turnOnBulbUseCase()
            if (isOn.isSuccess) {
                _bulbStates post Result.success(true)
            }
        }
    }

    fun turnBulbOff() {
        viewModelScope.launch {
            val isOff = turnOffBulbUseCase()
            if (isOff.isSuccess) {
                _bulbStates post Result.success(false)
            }
        }
    }
}
