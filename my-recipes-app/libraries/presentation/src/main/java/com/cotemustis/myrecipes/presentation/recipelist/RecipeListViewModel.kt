package com.cotemustis.myrecipes.presentation.recipelist

import androidx.lifecycle.ViewModel
import com.cotemustis.myrecipes.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {
}