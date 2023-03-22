package com.cotemustis.myrecipes.data.database.mapper

import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel
import com.cotemustis.myrecipes.domain.model.Recipe

internal fun RecipeDatabaseModel.mapToDomainModel() =
    Recipe(
        id = id,
        name = name,
        image = image,
        ingredients = ingredients,
        latitude = latitude,
        longitude = longitude,
        preparation = preparation
    )

internal fun Recipe.mapFromDomainModel() =
    RecipeDatabaseModel(
        id = id,
        name = name,
        image = image,
        ingredients = ingredients,
        latitude = latitude,
        longitude = longitude,
        preparation = preparation
    )