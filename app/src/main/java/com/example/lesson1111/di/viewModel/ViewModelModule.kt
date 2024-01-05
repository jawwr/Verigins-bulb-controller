package com.example.lesson1111.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lesson1111.presentation.BulbBrightnessViewModel
import com.example.lesson1111.presentation.BulbColorViewModel
import com.example.lesson1111.presentation.BulbStateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BulbStateViewModel::class)
    abstract fun bindBulbStateViewModel(viewModel: BulbStateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BulbColorViewModel::class)
    abstract fun bindBulbColorViewModel(viewModel: BulbColorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BulbBrightnessViewModel::class)
    abstract fun bindBulbBrightnessViewModel(viewModel: BulbBrightnessViewModel): ViewModel
}
