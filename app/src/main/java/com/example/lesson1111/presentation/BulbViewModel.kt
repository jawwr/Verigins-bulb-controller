package com.example.lesson1111.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson1111.UiState
import com.example.lesson1111.domain.GetBrightnessUseCase
import com.example.lesson1111.domain.GetBulbColorUseCase
import com.example.lesson1111.domain.GetBulbStateUseCase
import com.example.lesson1111.domain.GetCurrentColorUseCase
import com.example.lesson1111.domain.SetBrightnessUseCase
import com.example.lesson1111.domain.TurnOffBulbUseCase
import com.example.lesson1111.domain.TurnOnBulbUseCase
import com.example.lesson1111.domain.UpdateBulbColorUseCase
import com.example.lesson1111.util.post
import kotlinx.coroutines.launch
import javax.inject.Inject

class BulbViewModel @Inject constructor(
    private val getBulbStateUseCase: GetBulbStateUseCase,
    private val turnOnBulbUseCase: TurnOnBulbUseCase,
    private val turnOffBulbUseCase: TurnOffBulbUseCase,
    private val getBulbColorUseCase: GetBulbColorUseCase,
    private val updateBulbColorUseCase: UpdateBulbColorUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase,
    private val getBrightnessUseCase: GetBrightnessUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase
) : ViewModel() {
    private val _bulbStates = MutableLiveData<UiState<BulbState>>()
    val bulbStates: LiveData<UiState<BulbState>>
        get() = _bulbStates

    init {
        setup()
    }

    fun setBrightness(level: Int) {
        viewModelScope.launch {
            val result = setBrightnessUseCase(level)
            if (result.getOrNull() == true) {
                _bulbStates post (_bulbStates.value as? UiState.Success)?.value?.copy(
                    brightness = UiState.Success(level)
                )
            }
        }
    }

    fun setColor(color: String) {
        viewModelScope.launch {
            val result = updateBulbColorUseCase(color)
            if (result.getOrNull() == true) {
                _bulbStates post (_bulbStates.value as? UiState.Success)?.value?.copy(
                    currentColor = UiState.Success(color)
                )
            }
        }
    }

    fun turnBulbOn() {
        viewModelScope.launch {
            val result = turnOnBulbUseCase()
            if (_bulbStates.value is UiState.Failure) {
                setup()
            }
            _bulbStates post (_bulbStates.value as? UiState.Success)?.value?.copy(
                bulbState = result.let {
                    if (it.isSuccess) {
                        Result.success(true)
                    } else {
                        Result.success(false)
                    }
                }.toUiState()
            )
        }
    }

    fun turnBulbOff() {
        viewModelScope.launch {
            val result = turnOffBulbUseCase()
            _bulbStates post (_bulbStates.value as? UiState.Success)?.value?.copy(
                bulbState = result.let {
                    if (it.isSuccess) {
                        Result.success(false)
                    } else {
                        Result.success(true)
                    }
                }.toUiState()
            )
        }
    }

    private fun setup() {
        viewModelScope.launch {
            val currentColor = getCurrentColorUseCase().getOrNull()
                ?.let { Result.success(it.color) }
                .toUiStateOrElse { UiState.Failure("Ошибка получения текущего цвета") }

            val allColors = getBulbColorUseCase().getOrNull()
                ?.let { Result.success(it) }
                .toUiStateOrElse { UiState.Failure("Ошибка получения всех цветов") }

            val state = getBulbStateUseCase().toUiState()

            val brightness = getBrightnessUseCase().toUiState()

            val bulbState = BulbState(
                bulbState = state,
                colors = allColors,
                currentColor = currentColor,
                brightness = brightness
            )

            _bulbStates post if (bulbState.isSuccess()) bulbState else null
        }
    }

    private fun <T> Result<T?>?.toUiState(): UiState<T> {
        return if (this?.isSuccess == true) {
            getOrNull()?.let { UiState.Success(it) } ?: UiState.Failure("Что-то пошло не так")
        } else {
            UiState.Failure("Что-то пошло не так")
        }
    }

    private fun <T> Result<T?>?.toUiStateOrElse(func: () -> UiState<T>): UiState<T> {
        return this?.getOrNull()?.let { UiState.Success(it) } ?: func()
    }
}