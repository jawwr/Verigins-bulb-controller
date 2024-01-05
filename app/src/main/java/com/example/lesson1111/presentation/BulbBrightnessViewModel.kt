package com.example.lesson1111.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson1111.UiState
import com.example.lesson1111.domain.GetBrightnessUseCase
import com.example.lesson1111.domain.SetBrightnessUseCase
import com.example.lesson1111.util.post
import kotlinx.coroutines.launch
import javax.inject.Inject

class BulbBrightnessViewModel @Inject constructor(
    private val getBrightnessUseCase: GetBrightnessUseCase,
    private val setBrightnessUseCase: SetBrightnessUseCase
) : ViewModel() {
    private val _brightnesses: MutableLiveData<UiState<Int>> = MutableLiveData()
    val brightnesses: LiveData<UiState<Int>>
        get() = _brightnesses

    fun getBrightness() {
        viewModelScope.launch {
            _brightnesses post getBrightnessUseCase()
        }
    }

    fun setBrightness(level: Int) {
        viewModelScope.launch {
            setBrightnessUseCase(level)
        }
    }
}