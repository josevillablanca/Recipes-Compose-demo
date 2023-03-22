package com.cotemustis.myrecipes.data.network.model

import com.google.gson.annotations.SerializedName

internal data class RecipesNetworkResponse(
    @SerializedName("recipes")
    val recipeList: List<RecipeNetworkModel>
)
