package com.example.lesson1111.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson1111.R
import com.example.lesson1111.UiState
import com.example.lesson1111.databinding.FragmentSampleBinding
import com.example.lesson1111.di.ViewModelFactory
import com.example.lesson1111.di.appComponent
import javax.inject.Inject

class SampleFragment : Fragment(R.layout.fragment_sample) {
    private val binding: FragmentSampleBinding by viewBinding()
    private val adapter = TextViewAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: SampleViewModel by viewModels() { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBulbState()
        viewModel.jokesCategories.observe(viewLifecycleOwner) {
            showState(it)
        }
        viewModel.getBulbState()
        binding.stateOn.setOnClickListener { viewModel.turnBulbOn() }
        binding.stateOff.setOnClickListener { viewModel.turnBulbOff() }
    }

    private fun showState(uiState: UiState<Boolean>) {
        when (uiState) {
            is UiState.Success -> {
                binding.bulbState.text = if (uiState.value) "On" else "Off"
                binding.progressCategories.visibility = View.GONE
            }

            is UiState.Loading -> {
                binding.progressCategories.visibility = View.VISIBLE
            }

            is UiState.Failure -> {
                binding.progressCategories.visibility = View.GONE
            }

            else -> {}
        }
    }

//    private fun initRecycler() = with(binding.) {
//        layoutManager = LinearLayoutManager(requireContext())
//        adapter = this@SampleFragment.adapter
//    }

//    private fun showCategoriesList(uiState: UiState<Boolean>) {
//        when (uiState) {
//            is UiState.Success -> {
//                binding.categoriesRecyclerView.visibility = View.VISIBLE
//                binding.progressCategories.visibility = View.GONE
//
////                adapter.submit(uiState.value)
//            }
//
//            is UiState.Loading -> {
//                binding.categoriesRecyclerView.visibility = View.GONE
//                binding.progressCategories.visibility = View.VISIBLE
//            }
//
//            is UiState.Failure -> {
//                binding.categoriesRecyclerView.visibility = View.GONE
//                binding.progressCategories.visibility = View.GONE
//            }
//        }
//    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}
