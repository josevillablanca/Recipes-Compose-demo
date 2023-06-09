package com.cotemustis.myrecipes.domain.model

data class Recipe(
    val id: Long,
    val name: String,
    val image: String,
    val ingredients: List<String>,
    val latitude: Double,
    val longitude: Double,
    val preparation: String
)
