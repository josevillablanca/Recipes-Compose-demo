package com.cotemustis.myrecipes.data.di

import android.content.Context
import androidx.room.Room
import com.cotemustis.myrecipes.data.database.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context = context, RecipesDatabase::class.java, "recipes.db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDatabaseDao(recipesDatabase: RecipesDatabase) = recipesDatabase.recipesDao()
}