package com.cotemustis.myrecipes.domain.usecase

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
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
internal class GetRecipesFromSearchUseCaseTest {

    @MockK
    private lateinit var repository: RecipesRepository

    private lateinit var getRecipesFromSearchUseCase: GetRecipesFromSearchUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getRecipesFromSearchUseCase = GetRecipesFromSearchUseCase(repository)
    }

    @Test
    fun `use case should call repository using a search word and return a list of recipes`() =
        runTest {
            //Given
            coEvery { repository.getRecipesFromSearch(any()) } returns ResultState.Success(
                DomainMock.recipeList.subList(0, 1)
            )

            //when
            val result = getRecipesFromSearchUseCase("Receta")

            //then
            coVerify { repository.getRecipesFromSearch(any()) }
            assertEquals(
                DomainMock.recipeList.subList(0, 1),
                (result as ResultState.Success<List<Recipe>>).data
            )
        }
}