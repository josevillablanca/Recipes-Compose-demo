package com.cotemustis.myrecipes.data.network.mapper

import com.cotemustis.myrecipes.data.network.model.RecipeNetworkModel
import com.cotemustis.myrecipes.domain.model.Recipe

internal fun RecipeNetworkModel.mapToDomainModel() =
    Recipe(
        id = identifier,
        name = name,
        image = image,
        ingredients = ingredients,
        latitude = latitude,
        longitude = longitude,
        preparation = preparation
    )