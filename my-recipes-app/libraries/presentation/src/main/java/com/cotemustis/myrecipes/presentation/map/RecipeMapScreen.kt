package com.cotemustis.myrecipes.presentation.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cotemustis.myrecipes.presentation.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RecipeMapScreen(
    navController: NavHostController,
    mapViewModel: RecipeMapViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val latlngState by mapViewModel.latlngState
    val recipeNameState by mapViewModel.recipeNameState
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
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
        RecipeOriginMapView(recipeNameState, latlngState)
    }

}

@Composable
fun RecipeOriginMapView(recipeNameState: String, latlngState: LatLng) {

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latlngState, 10f)
    }
    Box(Modifier.fillMaxSize()) {
        GoogleMap(cameraPositionState = cameraPositionState) {
            Marker(
                state = MarkerState(position = latlngState),
                title = recipeNameState
            )
        }
    }
}
