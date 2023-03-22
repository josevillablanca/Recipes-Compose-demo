package com.cotemustis.myrecipes.presentation.map

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeMapViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val latitude
        get() = savedStateHandle.get<String>("latitude")?.toDouble()
            ?: throw IllegalStateException("Parameter recipeId must not be null!")

    private val longitude
        get() = savedStateHandle.get<String>("longitude")?.toDouble()
            ?: throw IllegalStateException("Parameter recipeId must not be null!")

    private val name
        get() = savedStateHandle.get<String>("name")
            ?: throw IllegalStateException("Parameter recipeId must not be null!")

    private val _latlngState by lazy { mutableStateOf(LatLng(latitude, longitude)) }
    val latlngState: State<LatLng> by lazy { _latlngState }

    private val _recipeNameState by lazy { mutableStateOf(name) }
    val recipeNameState: State<String> by lazy { _recipeNameState }

}
