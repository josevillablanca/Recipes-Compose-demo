package com.cotemustis.myrecipes.domain.repository

import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipes(): Flow<ResultState<List<Recipe>>>
    suspend fun getRecipeById(recipeId: Long): ResultState<Recipe>
    suspend fun getRecipesFromSearch(searchText: String): ResultState<List<Recipe>>
}