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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.R
import com.cotemustis.myrecipes.presentation.utils.NavRoutes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeDetailScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = detailModel().name)
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
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            DetailHeader()
            DetailContent()
        }
    }

}

@Composable
fun DetailHeader() {
    val context = LocalContext.current
    Box(Modifier.fillMaxWidth()) {
        AsyncImage(
            model = ImageRequest.Builder(context).data(detailModel().image).crossfade(true).build(),
            contentDescription = "Detail Recipe Image",
            placeholder = rememberVectorPainter(Icons.Filled.BrokenImage),
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(200.dp)
        )
    }
}

@Composable
fun DetailContent() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IngredientCard()
        PreparationCard()
    }
}

@Composable
fun IngredientCard() {
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
            Text(text = detailModel().ingredients, fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun PreparationCard() {
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
            Text(text = detailModel().ingredients, fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
    }
}

//Only for test
fun detailModel(): Recipe =
    Recipe(
        "Pollo al horno con finas hierbas",
        "https://i.blogs.es/6cb690/1366_2000-6/1366_2000.jpg",
        "1 pollo entero de aproximadamente 1,8 kg, 1 limón, 1 cebolla, hierbas al gusto (albahaca, tomillo, romero, etc), 10 g de manteca de cerdo y 3 patatas de guarnición.",
        -33.59266164917812,
        -71.60426775304816
    )
