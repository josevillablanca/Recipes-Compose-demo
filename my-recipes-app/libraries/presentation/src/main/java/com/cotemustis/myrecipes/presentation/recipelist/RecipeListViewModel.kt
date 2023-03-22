package com.cotemustis.myrecipes.presentation.recipelist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cotemustis.myrecipes.domain.usecase.GetRecipesFromSearchUseCase
import com.cotemustis.myrecipes.domain.usecase.GetRecipesUseCase
import com.cotemustis.myrecipes.domain.utils.ResultState
import com.cotemustis.myrecipes.presentation.recipelist.uistate.RecipeListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val getRecipesFromSearchUseCase: GetRecipesFromSearchUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = RecipeListUiState.Error
    }

    private val _uiState by lazy { mutableStateOf<RecipeListUiState>(RecipeListUiState.Loading) }
    val uiState: State<RecipeListUiState> by lazy { _uiState.apply { getRecipesList() } }

    private val _searchInputTextState by lazy { mutableStateOf("") }
    val searchInputTextState: State<String> by lazy { _searchInputTextState }

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

    private fun getFilteredRecipes(searchText: String) {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = RecipeListUiState.Loading
            _uiState.value = when (val result = getRecipesFromSearchUseCase(searchText)) {
                is ResultState.Error -> RecipeListUiState.ErrorSearch
                is ResultState.Success -> RecipeListUiState.ShowRecipes(result.data)
            }
        }
    }

    fun onSearchInputChanged(searchText: String) {
        _searchInputTextState.value = searchText
        getFilteredRecipes(searchText)
    }

    fun onRetryLoad() {
        getRecipesList()
    }

    fun refresh() {
        viewModelScope.launch(exceptionHandler) {
            _searchInputTextState.value = ""
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