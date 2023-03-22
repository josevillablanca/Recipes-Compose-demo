package com.cotemustis.myrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cotemustis.myrecipes.presentation.detail.RecipeDetailScreen
import com.cotemustis.myrecipes.presentation.map.RecipeMapScreen
import com.cotemustis.myrecipes.presentation.recipelist.RecipeListScreen
import com.cotemustis.myrecipes.presentation.utils.NavRoutes.*
import com.cotemustis.myrecipes.presentation.theme.MyRecipesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyRecipesTheme {
                NavHost(navController = navController, startDestination = RecipeListRoute.route) {
                    composable(RecipeListRoute.route) {
                        RecipeListScreen(navController)
                    }
                    composable(
                        RecipeDetailRoute.route,
                        arguments = listOf(navArgument("recipeId") { type = NavType.LongType })
                    ) {
                        RecipeDetailScreen(navController)
                    }
                    composable(
                        RecipeMapRoute.route,
                        arguments = listOf(
                            navArgument("latitude") { type = NavType.StringType },
                            navArgument("longitude") { type = NavType.StringType })
                    ) {
                        RecipeMapScreen(navController)
                    }
                }
            }
        }
    }
}