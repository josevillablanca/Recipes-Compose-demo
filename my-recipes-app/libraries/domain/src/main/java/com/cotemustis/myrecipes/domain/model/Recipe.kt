package com.cotemustis.myrecipes.domain.model

data class Recipe(
    val name: String,
    val image: String,
    val ingredients: String,
    val latitude: Double,
    val longitude: Double,
    val preparation: String = ""
)
