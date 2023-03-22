package com.cotemustis.myrecipes.presentation.recipelist

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.R
import com.cotemustis.myrecipes.presentation.ui.RecipeToolbar
import com.cotemustis.myrecipes.presentation.utils.NavRoutes.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeListScreen(
    navController: NavHostController,
    recipeListViewModel: RecipeListViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val searchInputState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column {
            RecipeSearchView(searchInputState)
            RecipesListView(searchInputState) {
                navController.navigate(RecipeDetailRoute.route)
            }
        }
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

@Composable
fun RecipesListView(searchInputState: MutableState<TextFieldValue>, onItemClick: (Recipe) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        items() { recipe ->
//            RecipeItemView(recipe) {
//                onItemClick(recipe)
//            }
//        }
    }
}
