package com.cotemustis.myrecipes.data.datasource

import com.cotemustis.myrecipes.data.database.RecipesDao
import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSource
import com.cotemustis.myrecipes.data.datasource.localdatasource.RecipesLocalDataSourceImpl
import com.cotemustis.myrecipes.data.mock.RecipesMock
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
internal class RecipesLocalDataSourceTest {

    @MockK
    private lateinit var recipesDao: RecipesDao

    private lateinit var recipesLocalDataSource: RecipesLocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        recipesLocalDataSource = RecipesLocalDataSourceImpl(recipesDao)
    }

    @Test
    fun `get list of recipes with success`() = runTest {
        //given
        coEvery { recipesDao.getRecipes() } returns RecipesMock.recipesDatabaseModelListMock

        //when
        val result = recipesLocalDataSource.getRecipes()

        //then
        coVerify { recipesDao.getRecipes() }
        assertEquals(result, RecipesMock.recipesDatabaseModelListMock)
    }

    @Test
    fun `insert list of recipes with success`() = runTest {
        //given
        val recipesList = RecipesMock.recipesDatabaseModelListMock
        coEvery { recipesDao.updateOrInsertRecipes(any()) } returns Unit

        //when
        recipesLocalDataSource.insertRecipes(recipesList)

        //then
        coVerify { recipesDao.updateOrInsertRecipes(any()) }
    }

    @Test
    fun `get a recipe by some id`() = runTest {
        //given
        coEvery { recipesDao.getRecipeById(any()) } returns RecipesMock.recipesDatabaseModelListMock[0]

        //when
        val result = recipesLocalDataSource.getRecipeById(1)

        //then
        coVerify { recipesDao.getRecipeById(any()) }
        assertEquals(result, RecipesMock.recipesDatabaseModelListMock[0])
    }

    @Test
    fun `get recipe list from search word`() = runTest {
        //given
        val searchString = "Receta"
        coEvery { recipesDao.getRecipesFromSearch(any()) } returns RecipesMock.recipesDatabaseModelListMock.subList(
            0,
            2
        )

        //when
        val result = recipesLocalDataSource.getRecipesFromSearch(searchString)

        coVerify { recipesDao.getRecipesFromSearch(any()) }
        assertEquals(result, RecipesMock.recipesDatabaseModelListMock.subList(0, 2))
    }
}