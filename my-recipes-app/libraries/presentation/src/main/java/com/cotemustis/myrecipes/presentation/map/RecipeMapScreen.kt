package com.cotemustis.myrecipes.presentation.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeMapScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.recipe_map_title))
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
        }
    ) {
        RecipeOriginMapView()
    }

}

@Composable
fun RecipeOriginMapView() {
    val recipe = detailModel()
    val originPlace = LatLng(recipe.latitude, recipe.longitude)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(originPlace, 11f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(
                state = MarkerState(position = originPlace),
                title = recipe.name
            )
        }
    }
}


fun detailModel(): Recipe =
    Recipe(
        "Pollo al horno con finas hierbas",
        "https://i.blogs.es/6cb690/1366_2000-6/1366_2000.jpg",
        "1 pollo entero de aproximadamente 1,8 kg, 1 limón, 1 cebolla, hierbas al gusto (albahaca, tomillo, romero, etc), 10 g de manteca de cerdo y 3 patatas de guarnición.",
        -33.59266164917812,
        -71.60426775304816
    )