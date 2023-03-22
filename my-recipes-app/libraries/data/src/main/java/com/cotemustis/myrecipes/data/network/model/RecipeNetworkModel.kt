package com.cotemustis.myrecipes.data.network.model

internal data class RecipeNetworkModel(
    val identifier: Long,
    val name: String,
    val image: String,
    val ingredients: List<String>,
    val latitude: Double,
    val longitude: Double,
    val preparation: String
)
