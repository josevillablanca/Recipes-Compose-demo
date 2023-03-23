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
internal class GetRecipeByIdUseCaseTest {

    @MockK
    private lateinit var repository: RecipesRepository

    private lateinit var getRecipeByIdUseCase: GetRecipeByIdUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getRecipeByIdUseCase = GetRecipeByIdUseCase(repository)
    }

    @Test
    fun `use case invoke should call a repository and get a recipe`() = runTest {
        //given
        coEvery { repository.getRecipeById(any()) } returns ResultState.Success(DomainMock.recipeList[0])

        //when
        val result = getRecipeByIdUseCase(1)

        //then
        coVerify { repository.getRecipeById(any()) }
        assertEquals(DomainMock.recipeList[0], (result as ResultState.Success<Recipe>).data)
    }
}