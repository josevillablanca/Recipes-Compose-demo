package com.cotemustis.myrecipes.presentation.recipelist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.R
import com.cotemustis.myrecipes.presentation.recipelist.uistate.RecipeListUiState
import com.cotemustis.myrecipes.presentation.utils.NavRoutes.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeListScreen(
    navController: NavHostController,
    recipeListViewModel: RecipeListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val searchInputState = remember { mutableStateOf(TextFieldValue("")) }
    val uiState by recipeListViewModel.uiState
    val isRefreshing by recipeListViewModel.isRefreshing.collectAsState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { recipeListViewModel.refresh() })

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(Modifier.fillMaxSize()) {
            when (uiState) {
                RecipeListUiState.Error -> {
                    ShowErrorView { recipeListViewModel.onRetryLoad() }
                }
                RecipeListUiState.Loading -> ShowLoader()
                is RecipeListUiState.ShowRecipes -> {
                    Column {
                        RecipeSearchView(searchInputState)
                        RecipesListView(
                            (uiState as RecipeListUiState.ShowRecipes).recipes,
                            pullRefreshState,
                            isRefreshing
                        ) {
                            navController.navigate(RecipeDetailRoute.route)
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun ShowErrorView(onRetryLoad: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.recipes_list_error_text)
        )
        Button(
            onClick = { onRetryLoad() },
            content = {
                Text(
                    text = stringResource(id = R.string.retry),
                    style = MaterialTheme.typography.body1,
                )
            }
        )
    }
}

@Composable
fun RecipeSearchView(searchInputState: MutableState<TextFieldValue>) {
    TextField(
        value = searchInputState.value,
        onValueChange = { value ->
            searchInputState.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "search icon",
                modifier = Modifier
                    .padding(16.dp)
                    .size(24.dp)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        placeholder = {
            Text(text = stringResource(R.string.search_placeholder_text))
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@ExperimentalMaterialApi
@Composable
fun RecipesListView(
    recipes: List<Recipe>,
    pullRefreshState: PullRefreshState,
    isRefreshing: Boolean,
    onItemClick: (Recipe) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.pullRefresh(pullRefreshState)
        ) {
            items(recipes) { recipe ->
                RecipeItemView(recipe) {
                    onItemClick(recipe)
                }
            }
        }
    }

}

@Composable
private fun ShowLoader() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}
