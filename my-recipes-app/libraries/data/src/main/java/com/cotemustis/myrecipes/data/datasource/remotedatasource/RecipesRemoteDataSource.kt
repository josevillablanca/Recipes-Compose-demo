package com.cotemustis.myrecipes.data.datasource.remotedatasource

import com.cotemustis.myrecipes.data.network.model.RecipeNetworkModel

internal interface RecipesRemoteDataSource {
    suspend fun getRecipes(): List<RecipeNetworkModel>
}