package com.cotemustis.myrecipes.domain.usecase

import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.repository.RecipesRepository
import com.cotemustis.myrecipes.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesFromSearchUseCase @Inject constructor(
    private val repository: RecipesRepository
) {
    suspend operator fun invoke(searchText: String): ResultState<List<Recipe>> =
        repository.getRecipesFromSearch(searchText)
}