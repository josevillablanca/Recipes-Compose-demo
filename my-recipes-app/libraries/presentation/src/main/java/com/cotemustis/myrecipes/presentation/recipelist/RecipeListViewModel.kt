package com.cotemustis.myrecipes.presentation.recipelist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotemustis.myrecipes.domain.usecase.GetRecipesUseCase
import com.cotemustis.myrecipes.domain.utils.ResultState
import com.cotemustis.myrecipes.presentation.recipelist.uistate.RecipeListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = RecipeListUiState.Error
    }

    private val _uiState by lazy { mutableStateOf<RecipeListUiState>(RecipeListUiState.Loading) }
    val uiState: State<RecipeListUiState> by lazy { _uiState.apply { getRecipesList() } }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    private fun getRecipesList() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = RecipeListUiState.Loading
            getRecipesUseCase().collectLatest { result ->
                _uiState.value = when (result) {
                    is ResultState.Error -> RecipeListUiState.Error
                    is ResultState.Success -> RecipeListUiState.ShowRecipes(result.data)
                }
            }
        }
    }

    fun onRetryLoad() {
        getRecipesList()
    }

    fun refresh() {
        viewModelScope.launch(exceptionHandler) {
            _isRefreshing.emit(true)
            getRecipesUseCase().collectLatest { result ->
                _uiState.value = when (result) {
                    is ResultState.Error -> RecipeListUiState.Error
                    is ResultState.Success -> RecipeListUiState.ShowRecipes(result.data)
                }
            }
            _isRefreshing.emit(false)
        }
    }
}