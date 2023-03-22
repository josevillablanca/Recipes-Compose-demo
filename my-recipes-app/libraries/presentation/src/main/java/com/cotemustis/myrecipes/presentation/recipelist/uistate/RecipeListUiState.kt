package com.cotemustis.myrecipes.presentation.recipelist.uistate

import com.cotemustis.myrecipes.domain.model.Recipe

sealed class RecipeListUiState {
    object Loading : RecipeListUiState()
    object Error : RecipeListUiState()
    data class ShowRecipes(val recipes: List<Recipe>) : RecipeListUiState()
    object ErrorSearch : RecipeListUiState()

}