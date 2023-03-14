package com.cotemustis.myrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        RecipeListScreen()
                    }
                    composable(RecipeDetailRoute.route) {
                        RecipeDetailScreen()
                    }
                    composable(RecipeMapRoute.route) {
                        RecipeMapScreen()
                    }
                }
            }
        }
    }
}