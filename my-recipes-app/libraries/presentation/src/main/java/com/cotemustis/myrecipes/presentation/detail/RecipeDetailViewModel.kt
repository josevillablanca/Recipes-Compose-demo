package com.cotemustis.myrecipes.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotemustis.myrecipes.domain.usecase.GetRecipeByIdUseCase
import com.cotemustis.myrecipes.domain.utils.ResultState
import com.cotemustis.myrecipes.presentation.detail.uistate.RecipeDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = RecipeDetailUiState.Error
    }

    private val recipeId
        get() = savedStateHandle.get<Long>("recipeId")
            ?: throw IllegalStateException("Parameter recipeId must not be null!")

    private val _uiState by lazy { mutableStateOf<RecipeDetailUiState>(RecipeDetailUiState.Loading) }
    val uiState: State<RecipeDetailUiState> by lazy { _uiState.apply { getRecipe() } }

    private val _titleName by lazy { mutableStateOf("") }
    val titleName: State<String> by lazy { _titleName }

    private fun getRecipe() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = RecipeDetailUiState.Loading
            _uiState.value = when (val result = getRecipeByIdUseCase(recipeId)) {
                is ResultState.Error -> RecipeDetailUiState.Error
                is ResultState.Success -> {
                    _titleName.value = result.data.name
                    RecipeDetailUiState.ShowRecipe(result.data)
                }
            }
        }
    }

}