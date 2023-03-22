package com.cotemustis.myrecipes.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
internal data class RecipeDatabaseModel(
    @PrimaryKey
    val id: Long,
    val name: String,
    val image: String,
    val ingredients: List<String>,
    val latitude: Double,
    val longitude: Double,
    val preparation: String
)
