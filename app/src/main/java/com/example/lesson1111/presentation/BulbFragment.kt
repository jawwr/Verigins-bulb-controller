package com.example.lesson1111.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
    private val bulbStateViewModel: BulbStateViewModel by viewModels { viewModelFactory }
    private val bulbColorViewModel: BulbColorViewModel by viewModels { viewModelFactory }
    private val bulbBrightnessViewModel: BulbBrightnessViewModel by viewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun changeBulbState(switch: SwitchCompat) {
        if (switch.isChecked) {
            bulbStateViewModel.turnBulbOn()
        } else {
            bulbStateViewModel.turnBulbOff()
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val color = binding.bulbColorList.selectedItem as String
        bulbColorViewModel.setColor(color)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onStartTrackingTouch(slider: Slider) {
    }

    override fun onStopTrackingTouch(slider: Slider) {
        bulbBrightnessViewModel.setBrightness(slider.value.toInt())
    }

    private fun setBulbColorList(uiState: UiState<List<BulbColor>>) {
        if (uiState is UiState.Success) {
            val list = uiState.value
            binding.bulbColorList.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                list.map { it.color }
            ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        }
    }

    private fun showState(uiState: UiState<Boolean>) {
        when (uiState) {
            is UiState.Success -> {
                val isOn = uiState.value
                binding.bulbStateSwitch.isChecked = isOn
                binding.bulbState.text = if (isOn) "On" else "Off"
                binding.progressCategories.visibility = View.GONE
            }

            is UiState.Loading -> {
                binding.progressCategories.visibility = View.VISIBLE
            }

            is UiState.Failure -> {
                binding.progressCategories.visibility = View.GONE
            }
        }
    }

    private fun showBrightness(uiState: UiState<Int>) {
        if (uiState is UiState.Success) {
            binding.brightnessSlider.value = uiState.value.toFloat()
        }
    }

    private fun setup() {
        setupBulbStates()
        setupBulbColor()
        setupSlider()
    }

    private fun setupSlider() {
        binding.brightnessSlider.addOnSliderTouchListener(this)
        bulbBrightnessViewModel.brightnesses.observe(viewLifecycleOwner) {
            showBrightness(it)
        }
        bulbBrightnessViewModel.getBrightness()
    }

    private fun setupBulbStates() {
        bulbStateViewModel.bulbStates.observe(viewLifecycleOwner) {
            showState(it)
        }
        bulbStateViewModel.getBulbState()
        binding.bulbStateSwitch.setOnClickListener { changeBulbState(it as SwitchCompat) }
    }

    private fun setupBulbColor() {
        binding.bulbColorList.onItemSelectedListener = this
        binding.bulbColorList.isSelected = false

        bulbColorViewModel.bulbColors.observe(viewLifecycleOwner) {
            setBulbColorList(it)
        }
        bulbColorViewModel.getCurrentColor()
        bulbColorViewModel.getColors()
    }
}
