package com.example.lesson1111.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson1111.UiState
import com.example.lesson1111.data.model.BulbColor
import com.example.lesson1111.domain.GetBulbColorUseCase
import com.example.lesson1111.domain.GetCurrentColorUseCase
import com.example.lesson1111.domain.UpdateBulbColorUseCase
import com.example.lesson1111.util.post
import kotlinx.coroutines.launch
import javax.inject.Inject

class BulbColorViewModel @Inject constructor(
    private val getBulbColorUseCase: GetBulbColorUseCase,
    private val updateBulbColorUseCase: UpdateBulbColorUseCase,
    private val getCurrentColorUseCase: GetCurrentColorUseCase
) : ViewModel() {
    private val _bulbColors = MutableLiveData<UiState<List<BulbColor>>>()
    val bulbColors: LiveData<UiState<List<BulbColor>>>
        get() = _bulbColors

    fun setColor(color: String) {
        viewModelScope.launch {
            updateBulbColorUseCase(color)
        }
    }

    fun getCurrentColor() {
        viewModelScope.launch {
            val color = getCurrentColorUseCase()
            if (color.isSuccess) {
                _bulbColors post Result.success(listOf(requireNotNull(color.getOrNull())))
            }
        }
    }

    fun getColors() {
        viewModelScope.launch {
            _bulbColors post getBulbColorUseCase()
        }
    }
}