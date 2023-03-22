package com.cotemustis.myrecipes.data.di

import com.cotemustis.myrecipes.data.repository.RecipesRepositoryImpl
import com.cotemustis.myrecipes.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipesRepository(repository: RecipesRepositoryImpl): RecipesRepository =
        repository
}