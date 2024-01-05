package com.example.lesson1111.di

import com.example.lesson1111.di.viewModel.ViewModelModule
import com.example.lesson1111.presentation.BulbFragment
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: BulbFragment)
}

@Module(
    includes = [
        NetworkModule::class,
        ViewModelModule::class,
        AppBindsModule::class,
    ]
)
class AppModule
