package com.cotemustis.myrecipes.data.datasource.remotedatasource

import com.cotemustis.myrecipes.data.network.RecipesApi
import com.cotemustis.myrecipes.data.network.model.RecipeNetworkModel
import javax.inject.Inject

internal class RecipesRemoteDataSourceImpl @Inject constructor(
    private val recipesApi: RecipesApi
) : RecipesRemoteDataSource {
    override suspend fun getRecipes(): List<RecipeNetworkModel> {
        val response = recipesApi.getRecipes()
        return response.recipeList
    }

}