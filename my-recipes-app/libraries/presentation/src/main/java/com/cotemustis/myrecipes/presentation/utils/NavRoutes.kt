package com.cotemustis.myrecipes.presentation.utils

sealed class NavRoutes(val route: String) {
    object RecipeListRoute : NavRoutes("recipeListRoute")
    object RecipeDetailRoute : NavRoutes("recipeDetailRoute/{recipeId}") {
        fun createRoute(recipeId: Long) = "recipeDetailRoute/$recipeId"
    }

    object RecipeMapRoute : NavRoutes("recipeMapRoute/{name}/{latitude}/{longitude}") {
        fun createRoute(name: String, latitude: String, longitude: String) =
            "recipeMapRoute/$name/$latitude/$longitude"
    }

}
