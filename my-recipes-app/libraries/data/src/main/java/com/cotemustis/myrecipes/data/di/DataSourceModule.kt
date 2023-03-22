package com.cotemustis.myrecipes.data.di

import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSource
import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSourceImpl
import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSource
import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DataSourceModule {

    @Provides
    @Singleton
    fun provideRecipesRemoteDataSource(remoteDataSource: RecipesRemoteDataSourceImpl): RecipesRemoteDataSource =
        remoteDataSource

    @Provides
    @Singleton
    fun provideRecipesLocalDataSource(localDataSource: RecipesLocalDataSourceImpl): RecipesLocalDataSource =
        localDataSource
}