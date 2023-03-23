package com.cotemustis.myrecipes.domain.usecase

import app.cash.turbine.test
import com.cotemustis.myrecipes.domain.mock.DomainMock
import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.repository.RecipesRepository
import com.cotemustis.myrecipes.domain.utils.ResultState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetRecipesUseCaseTest {
    @MockK
    private lateinit var repository: RecipesRepository

    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getRecipesUseCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `use case should call repository and return a list of recipes`() = runTest {
        //Given
        coEvery { repository.getRecipes() } returns flowOf(ResultState.Success(DomainMock.recipeList))

        //when
        getRecipesUseCase().test {
            val result = awaitItem()
            awaitComplete()

            //then
            coVerify { repository.getRecipes() }
            assertEquals(DomainMock.recipeList, (result as ResultState.Success<List<Recipe>>).data)
        }
    }
}