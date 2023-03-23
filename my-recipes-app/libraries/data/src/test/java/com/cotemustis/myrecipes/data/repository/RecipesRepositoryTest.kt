package com.cotemustis.myrecipes.data.repository

import app.cash.turbine.test
import com.cotemustis.myrecipes.data.database.mapper.mapToDomainModel
import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSource
import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSource
import com.cotemustis.myrecipes.data.mock.RecipesMock
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.repository.RecipesRepository
import com.cotemustis.myrecipes.domain.utils.ResultState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RecipesRepositoryTest {

    @MockK
    private lateinit var remoteDataSource: RecipesRemoteDataSource

    @MockK
    private lateinit var localDataSource: RecipesLocalDataSource

    private lateinit var repository: RecipesRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = RecipesRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `call get recipes and get them locally saved`() = runTest {
        //given
        val recipesList = RecipesMock.recipesDatabaseModelListMock
        coEvery { localDataSource.getRecipes() } returns recipesList

        //when
        repository.getRecipes().test {
            val localData = awaitItem()
            awaitComplete()

            //then
            coVerify { localDataSource.getRecipes() }
            assertEquals(
                recipesList.map { it.mapToDomainModel() },
                (localData as ResultState.Success<List<Recipe>>).data
            )
        }
    }

    @Test
    fun `call get recipes with empty list from local data source and error from remote data source must return error state`() =
        runTest {
            //given
            coEvery { localDataSource.getRecipes() } returns emptyList()
            coEvery { remoteDataSource.getRecipes() } throws Exception("error")

            //when
            repository.getRecipes().test {
                val result = awaitItem()
                awaitComplete()

                //then
                coVerify { localDataSource.getRecipes() }
                coVerify { remoteDataSource.getRecipes() }
                assert(result is ResultState.Error)
            }
        }

    @Test
    fun `call get recipes returns list of fetched recipes when local data source is empty`() =
        runTest {
            //given
            val recipeList = RecipesMock.recipesDatabaseModelListMock
            coEvery { localDataSource.getRecipes() } returns recipeList
            coEvery { remoteDataSource.getRecipes() } returns RecipesMock.recipesNetworkModelListMock
            coEvery { localDataSource.insertRecipes(any()) } returns Unit

            //when
            repository.getRecipes().test {
                val result = awaitItem()
                awaitComplete()

                //then
                coVerify { remoteDataSource.getRecipes() }
                coVerify { localDataSource.insertRecipes(any()) }
                assertEquals(
                    recipeList.map { it.mapToDomainModel() },
                    (result as ResultState.Success<List<Recipe>>).data
                )
            }
        }

    @Test
    fun `get recipe by id returns success data`() = runTest {
        //given
        val recipe = RecipesMock.recipesDatabaseModelListMock[0]
        coEvery { localDataSource.getRecipeById(any()) } returns recipe

        //when
        val result = repository.getRecipeById(1)

        //then
        coVerify { localDataSource.getRecipeById(any()) }
        assertEquals(recipe.mapToDomainModel(), (result as ResultState.Success<Recipe>).data)
    }

    @Test
    fun `get recipe by id returns error state`() = runTest {
        //given
        coEvery { localDataSource.getRecipeById(any()) } throws Exception("Error")

        //when
        val result = repository.getRecipeById(1)

        //then
        coVerify { localDataSource.getRecipeById(any()) }
        assert(result is ResultState.Error)
    }

    @Test
    fun `get recipe list filtered by a word is success`() = runTest {
        //given
        val searchString = "Receta"
        val filteredRecipeList = RecipesMock.recipesDatabaseModelListMock.subList(0, 2)
        coEvery { localDataSource.getRecipesFromSearch(any()) } returns filteredRecipeList

        //when
        val result = repository.getRecipesFromSearch(searchString)

        //then
        coVerify { localDataSource.getRecipesFromSearch(any()) }
        assertEquals(
            filteredRecipeList.map { it.mapToDomainModel() },
            (result as ResultState.Success<List<Recipe>>).data
        )
    }

    @Test
    fun `get recipe list filtered by a word returns error state`() = runTest {
        //given
        val searchString = "Receta"
        coEvery { localDataSource.getRecipesFromSearch(any()) } throws Exception("Error")

        //when
        val result = repository.getRecipesFromSearch(searchString)

        //then
        coVerify { localDataSource.getRecipesFromSearch(any()) }
        assert(result is ResultState.Error)
    }
}