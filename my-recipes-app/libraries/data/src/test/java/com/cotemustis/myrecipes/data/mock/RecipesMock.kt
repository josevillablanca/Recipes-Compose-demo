package com.cotemustis.myrecipes.data.mock

import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel
import com.cotemustis.myrecipes.data.network.model.RecipeNetworkModel

internal object RecipesMock {
    val recipesDatabaseModelListMock = listOf(
        RecipeDatabaseModel(
            id = 1,
            name = "Receta 1",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 1"
        ),
        RecipeDatabaseModel(
            id = 2,
            name = "Receta 2",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 2"
        ),
        RecipeDatabaseModel(
            id = 3,
            name = "Receta 3",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 3"
        ),
        RecipeDatabaseModel(
            id = 4,
            name = "Receta 4",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 4"
        )
    )

    val recipesNetworkModelListMock = listOf(
        RecipeNetworkModel(
            identifier = 1,
            name = "Receta 1",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 1"
        ),
        RecipeNetworkModel(
            identifier = 2,
            name = "Receta 2",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 2"
        ),
        RecipeNetworkModel(
            identifier = 3,
            name = "Receta 3",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 3"
        ),
        RecipeNetworkModel(
            identifier = 4,
            name = "Receta 4",
            image = "imageurl",
            ingredients = listOf("ingrediente1", "ingrediente2", "ingrediente3"),
            latitude = -32.597415271,
            longitude = -55.3741698190,
            preparation = "Preparacion 4"
        )
    )
}


