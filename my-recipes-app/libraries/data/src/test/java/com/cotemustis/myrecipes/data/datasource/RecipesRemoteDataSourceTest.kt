package com.cotemustis.myrecipes.data.datasource

import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSource
import com.cotemustis.myrecipes.data.datasource.remotedatasource.RecipesRemoteDataSourceImpl
import com.cotemustis.myrecipes.data.mock.RecipesMock
import com.cotemustis.myrecipes.data.network.RecipesApi
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
internal class RecipesRemoteDataSourceTest {

    @MockK
    private lateinit var recipesApi: RecipesApi

    private lateinit var recipesRemoteDataSource: RecipesRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        recipesRemoteDataSource = RecipesRemoteDataSourceImpl(recipesApi)
    }

    @Test
    fun `get recipes list from network with success`() = runTest {
        //given
        coEvery { recipesApi.getRecipes() } returns RecipesMock.recipesNetworkResponse

        //when
        val response = recipesRemoteDataSource.getRecipes()

        //then
        coVerify { recipesApi.getRecipes() }
        assertEquals(response, RecipesMock.recipesNetworkResponse.recipeList)
    }
}