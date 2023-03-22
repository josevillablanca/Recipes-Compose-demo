package com.cotemustis.myrecipes.data.repository

import com.cotemustis.myrecipes.data.database.mapper.mapFromDomainModel
import com.cotemustis.myrecipes.data.database.mapper.mapToDomainModel
import com.cotemustis.myrecipes.data.database.model.RecipeDatabaseModel
import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSource
import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSource
import com.cotemustis.myrecipes.data.network.mapper.mapToDomainModel
import com.cotemustis.myrecipes.data.network.model.RecipeNetworkModel
import com.cotemustis.myrecipes.data.utils.singleSourceOfTruthLogic
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.repository.RecipesRepository
import com.cotemustis.myrecipes.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RecipesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipesRemoteDataSource,
    private val localDataSource: RecipesLocalDataSource
) : RecipesRepository {

    override suspend fun getRecipes(): Flow<ResultState<List<Recipe>>> =
        singleSourceOfTruthLogic(
            readLocalData = {
                localDataSource.getRecipes().map(RecipeDatabaseModel::mapToDomainModel)
            },
            readRemoteData = {
                remoteDataSource.getRecipes().map(RecipeNetworkModel::mapToDomainModel)
            },
            saveLocalData = { recipes ->
                localDataSource.insertRecipes(recipes.map(Recipe::mapFromDomainModel))
            }
        )
}