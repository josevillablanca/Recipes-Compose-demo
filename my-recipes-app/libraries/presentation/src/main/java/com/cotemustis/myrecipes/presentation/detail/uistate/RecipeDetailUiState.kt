package com.cotemustis.myrecipes.presentation.detail.uistate

import com.cotemustis.myrecipes.domain.model.Recipe

sealed class RecipeDetailUiState {
    object Loading : RecipeDetailUiState()
    object Error : RecipeDetailUiState()
    data class ShowRecipe(val recipe: Recipe) : RecipeDetailUiState()
}