package com.cotemustis.myrecipes.presentation.recipelist

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.presentation.ui.RecipeToolbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun RecipeListScreen() {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            RecipeToolbar(onClickItem = {
                Toast.makeText(context, "Search Pressed", Toast.LENGTH_SHORT).show()
            })
        },
        scaffoldState = scaffoldState
    ) {
        RecipesListView()
    }
}

@Composable
fun RecipesListView() {
    val context = LocalContext.current
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(getRecipes()) { recipe ->
            RecipeItemView(recipe) {
                Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            }
        }
    }
}


fun getRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            "Garbanzo con calamares",
            "https://i.blogs.es/646683/1366_2000-7/1366_2000.jpg",
            "Ingredientes para dos personas: 1 calamar, 1 cebolla, 1 tomate, 1 diente de ajo, 1 bote de garbanzos cocidos, aceite de oliva virgen extra y 300-400 ml de caldo de pescado.",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Rodaballo gratinado al horno con patatas",
            "https://i.blogs.es/cb33ec/1366_2000-5/1366_2000.jpg",
            ": 1 rodaballo mediano en filetes (u otro pescado blanco), 2 patatas, 1 cebolla dulce, 200 ml de nata líquida para cocinar, pimienta negra molida, sal, aceite de oliva virgen extra y perejil fresco (opcional).",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Pollo al horno con finas hierbas",
            "https://i.blogs.es/6cb690/1366_2000-6/1366_2000.jpg",
            "1 pollo entero de aproximadamente 1,8 kg, 1 limón, 1 cebolla, hierbas al gusto (albahaca, tomillo, romero, etc), 10 g de manteca de cerdo y 3 patatas de guarnición.",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Fabada fácil en olla de cocción lenta",
            "https://i.blogs.es/78c634/1366_2000-10/1366_2000.jpg",
            " 450 g de fabes, 1 cucharada de aceite de oliva virgen extra, 1 paquete de compango asturiano(chorizo, morcilla y tocino), 1 hoja de laurel, azafrán, agua y sal.",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Pizza cinco quesos",
            "https://i.blogs.es/c9a7b6/1366_2000-9/1366_2000.jpg",
            "1 masa para pizza, 3 cucharadas de salsa de tomate, cincos quesos (mozzarella, gorgonzola, cheddar, rulo de cabra y emmental) y orégano seco.",
            -33.59266164917812,
            -71.60426775304816
        ),

        Recipe(
            "Pizza cinco quesos",
            "https://i.blogs.es/c9a7b6/1366_2000-9/1366_2000.jpg",
            "1 masa para pizza, 3 cucharadas de salsa de tomate, cincos quesos (mozzarella, gorgonzola, cheddar, rulo de cabra y emmental) y orégano seco.",
            -33.59266164917812,
            -71.60426775304816
        ),

        Recipe(
            "Pizza cinco quesos",
            "https://i.blogs.es/c9a7b6/1366_2000-9/1366_2000.jpg",
            "1 masa para pizza, 3 cucharadas de salsa de tomate, cincos quesos (mozzarella, gorgonzola, cheddar, rulo de cabra y emmental) y orégano seco.",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Pizza cinco quesos",
            "https://i.blogs.es/c9a7b6/1366_2000-9/1366_2000.jpg",
            "1 masa para pizza, 3 cucharadas de salsa de tomate, cincos quesos (mozzarella, gorgonzola, cheddar, rulo de cabra y emmental) y orégano seco.",
            -33.59266164917812,
            -71.60426775304816
        ),
        Recipe(
            "Pizza cinco quesos",
            "https://i.blogs.es/c9a7b6/1366_2000-9/1366_2000.jpg",
            "1 masa para pizza, 3 cucharadas de salsa de tomate, cincos quesos (mozzarella, gorgonzola, cheddar, rulo de cabra y emmental) y orégano seco.",
            -33.59266164917812,
            -71.60426775304816
        )

    )
}