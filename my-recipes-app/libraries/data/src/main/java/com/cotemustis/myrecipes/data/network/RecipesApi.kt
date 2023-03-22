package com.cotemustis.myrecipes.data.network

import com.cotemustis.myrecipes.data.network.model.RecipesNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface RecipesApi {

    @GET("/recipes")
    suspend fun getRecipes(): RecipesNetworkResponse
}