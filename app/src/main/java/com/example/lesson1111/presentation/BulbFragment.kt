package com.example.lesson1111.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson1111.R
import com.example.lesson1111.UiState
import com.example.lesson1111.data.model.BulbColor
import com.example.lesson1111.databinding.FragmentSampleBinding
import com.example.lesson1111.di.appComponent
import com.example.lesson1111.di.viewModel.ViewModelFactory
import com.google.android.material.slider.Slider
import javax.inject.Inject

class BulbFragment : Fragment(R.layout.fragment_sample), AdapterView.OnItemSelectedListener,
    Slider.OnSliderTouchListener {
    private val binding: FragmentSampleBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val bulbViewModel: BulbViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val color = binding.bulbColorList.selectedItem as String
        bulbViewModel.setColor(color)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onStartTrackingTouch(slider: Slider) {
    }

    override fun onStopTrackingTouch(slider: Slider) {
        bulbViewModel.setBrightness(slider.value.toInt())
    }

    private fun changeBulbState(switch: SwitchCompat) {
        if (switch.isChecked) {
            bulbViewModel.turnBulbOn()
        } else {
            bulbViewModel.turnBulbOff()
        }
    }

    private fun setBulbColorList(
        uiState: UiState<List<BulbColor>>,
        uiStateCurrentColor: UiState<String>
    ) {
        if (uiState is UiState.Success && uiStateCurrentColor is UiState.Success) {
            val list = uiState.value.map { it.color }
            val index = list.indexOf(uiStateCurrentColor.value)
            val colorList = binding.bulbColorList
            if (colorList.adapter == null) {
                colorList.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    list
                ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
                colorList.setSelection(index)
            }
        }
    }

    private fun showState(uiState: UiState<Boolean>) {
        if (uiState is UiState.Success) {
            val isOn = uiState.value
            binding.bulbStateSwitch.isChecked = isOn
            binding.bulbState.text = if (isOn) "On" else "Off"
        } else {
            binding.bulbState.text = "Ошибка"
        }
    }

    private fun showBrightness(uiState: UiState<Int>) {
        if (uiState is UiState.Success) {
            binding.brightnessSlider.value = uiState.value.toFloat()
        }
    }

    private fun showBulb(uiState: UiState<BulbState>) {
        when (uiState) {
            is UiState.Success -> {
                if (!uiState.value.isSuccess()) {
                    return
                }
                val bulbState = uiState.value
                setBulbColorList(bulbState.colors, bulbState.currentColor)
                showState(bulbState.bulbState)
                showBrightness(bulbState.brightness)

                binding.progressCategories.visibility = View.GONE
            }

            is UiState.Loading -> {
                binding.progressCategories.visibility = View.VISIBLE
            }

            is UiState.Failure -> {
                Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Попробуйте включить лампочку", Toast.LENGTH_LONG).show()
                binding.progressCategories.visibility = View.GONE
            }
        }
    }

    private fun setup() {
        bulbViewModel.bulbStates.observe(viewLifecycleOwner) {
            showBulb(it)
        }
        binding.bulbStateSwitch.setOnClickListener { changeBulbState(it as SwitchCompat) }
        binding.bulbColorList.onItemSelectedListener = this
        binding.brightnessSlider.addOnSliderTouchListener(this)
    }
}
