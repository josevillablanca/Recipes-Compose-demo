package com.cotemustis.myrecipes.data.datasource.localdatasource

import com.cotemustis.myrecipes.data.database.RecipesDao
import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel
import javax.inject.Inject

internal class RecipesLocalDataSourceImpl @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLocalDataSource {

    override suspend fun getRecipes(): List<RecipeDatabaseModel> =
        recipesDao.getRecipes()

    override suspend fun insertRecipes(recipes: List<RecipeDatabaseModel>) =
        recipesDao.updateOrInsertRecipes(recipes)

    override suspend fun getRecipeById(recipeId: Long): RecipeDatabaseModel =
        recipesDao.getRecipeById(recipeId)

    override suspend fun getRecipesFromSearch(searchText: String): List<RecipeDatabaseModel> {
        val text = "%$searchText%"
        return recipesDao.getRecipesFromSearch(text)
    }


}