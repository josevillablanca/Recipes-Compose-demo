package com.cotemustis.myrecipes.domain.mock

import com.cotemustis.myrecipes.domain.model.Recipe

internal object DomainMock {
    val recipeList = listOf(
        Recipe(
            id = 1,
            name = "Receta 1",
            image = "image",
            ingredients = listOf("ingrediente 1", "ingrediente 2", "ingrediente 3"),
            latitude = 0.0,
            longitude = 0.0,
            preparation = "Preparacion 1"
        ),
        Recipe(
            id = 2,
            name = "Receta 2",
            image = "image",
            ingredients = listOf("ingrediente 1", "ingrediente 2", "ingrediente 3"),
            latitude = 0.0,
            longitude = 0.0,
            preparation = "Preparacion 2"
        ),
        Recipe(
            id = 3,
            name = "Receta 3",
            image = "image",
            ingredients = listOf("ingrediente 1", "ingrediente 2", "ingrediente 3"),
            latitude = 0.0,
            longitude = 0.0,
            preparation = "Preparacion 3"
        ),
        Recipe(
            id = 4,
            name = "Receta 4",
            image = "image",
            ingredients = listOf("ingrediente 1", "ingrediente 2", "ingrediente 3"),
            latitude = 0.0,
            longitude = 0.0,
            preparation = "Preparacion 4"
        )
    )
}