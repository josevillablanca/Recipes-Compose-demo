package com.cotemustis.myrecipes.presentation.utils

sealed class NavRoutes(val route: String) {
    object RecipeListRoute : NavRoutes("recipeListRoute")
    object RecipeDetailRoute : NavRoutes("recipeDetailRoute")
    object RecipeMapRoute : NavRoutes("recipeMapRoute")
}
