package com.cotemustis.myrecipes.presentation.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cotemustis.myrecipes.presentation.R

@Composable
fun RecipeToolbar(onClickItem: (ToolbarOptions) -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            IconButton(onClick = { onClickItem(ToolbarOptions.SEARCH) }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            }
        }
    )
}