package com.example.lesson1111.di

import com.example.lesson1111.data.repository.BulbRepository
import com.example.lesson1111.data.repository.BulbRepositoryImpl
import com.example.lesson1111.data.repository.SampleRepository
import com.example.lesson1111.data.repository.SampleRepositoryImpl
import com.example.lesson1111.domain.GetBulbStateUseCase
import com.example.lesson1111.domain.GetBulbStateUseCaseImpl
import com.example.lesson1111.domain.GetJokeUseCase
import com.example.lesson1111.domain.GetJokeUseCaseImpl
import com.example.lesson1111.domain.GetJokesCategoriesUseCase
import com.example.lesson1111.domain.GetJokesCategoriesUseCaseImpl
import com.example.lesson1111.domain.TurnOffBulbUseCase
import com.example.lesson1111.domain.TurnOffBulbUseCaseImpl
import com.example.lesson1111.domain.TurnOnBulbUseCase
import com.example.lesson1111.domain.TurnOnBulbUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindSampleRepository(repository: SampleRepositoryImpl): SampleRepository

    @Binds
    fun bindGetJokesCategoriesUseCase(useCaseImpl: GetJokesCategoriesUseCaseImpl): GetJokesCategoriesUseCase

    @Binds
    fun bindGetJokesUseCase(useCaseImpl: GetJokeUseCaseImpl): GetJokeUseCase

    @Binds
    fun bindBulbRepository(repository: BulbRepositoryImpl): BulbRepository

    @Binds
    fun bindGetState(useCase: GetBulbStateUseCaseImpl): GetBulbStateUseCase

    @Binds
    fun bindTurnOn(useCase: TurnOnBulbUseCaseImpl): TurnOnBulbUseCase

    @Binds
    fun bindTurnOff(useCase: TurnOffBulbUseCaseImpl): TurnOffBulbUseCase
}
