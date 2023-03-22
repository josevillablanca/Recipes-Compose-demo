package com.cotemustis.myrecipes.presentation.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.R
import com.cotemustis.myrecipes.presentation.detail.uistate.RecipeDetailUiState
import com.cotemustis.myrecipes.presentation.utils.NavRoutes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(
    navController: NavHostController,
    detailViewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val uiState by detailViewModel.uiState
    val titleNameState by detailViewModel.titleName

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = titleNameState)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                })
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(NavRoutes.RecipeMapRoute.route) }) {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "FAB Icon for map location"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        when (uiState) {
            RecipeDetailUiState.Error -> Text(text = stringResource(R.string.recipe_detail_error_text))
            RecipeDetailUiState.Loading -> CircularProgressIndicator()
            is RecipeDetailUiState.ShowRecipe -> {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    val recipe = (uiState as RecipeDetailUiState.ShowRecipe).recipe
                    DetailHeader(recipe)
                    DetailContent(recipe)
                }
            }
        }
    }

}

@Composable
fun DetailHeader(recipe: Recipe) {
    val context = LocalContext.current
    Box(Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(recipe.image).crossfade(true).build(),
            contentDescription = "Detail Recipe Image",
            placeholder = rememberVectorPainter(Icons.Filled.BrokenImage),
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(200.dp)
        )
    }
}

@Composable
fun DetailContent(recipe: Recipe) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IngredientCard(recipe)
        PreparationCard(recipe)
    }
}

@Composable
fun IngredientCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.ingredient_card_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            recipe.ingredients.forEach { ingredient ->
                Text(
                    text = ingredient,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }

        }
    }
}

@Composable
fun PreparationCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 12.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.preparation_card_title),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = recipe.preparation, fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
    }
}
