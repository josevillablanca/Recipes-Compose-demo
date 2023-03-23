package com.cotemustis.myrecipes.data.datasource.localdatasource

import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel

internal interface RecipesLocalDataSource {
    suspend fun getRecipes(): List<RecipeDatabaseModel>
    suspend fun insertRecipes(recipes: List<RecipeDatabaseModel>)
    suspend fun getRecipeById(recipeId: Long): RecipeDatabaseModel
    suspend fun getRecipesFromSearch(searchText: String): List<RecipeDatabaseModel>
}