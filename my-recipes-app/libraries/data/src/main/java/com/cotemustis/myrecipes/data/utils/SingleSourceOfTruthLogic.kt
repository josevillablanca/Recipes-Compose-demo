package com.cotemustis.myrecipes.data.utils

import com.cotemustis.myrecipes.domain.model.Recipe
import com.cotemustis.myrecipes.domain.utils.ResultState
import com.cotemustis.myrecipes.domain.utils.getResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal fun singleSourceOfTruthLogic(
    readLocalData: suspend () -> List<Recipe>,
    readRemoteData: suspend () -> List<Recipe>,
    saveLocalData: suspend (List<Recipe>) -> Unit
): Flow<ResultState<List<Recipe>>> = flow {

    val remoteData = getResult { readRemoteData() }
    if (remoteData is ResultState.Success && remoteData.data.isNotEmpty()) {
        saveLocalData(remoteData.data)
        val localDataUpdated = getResult { readLocalData() }
        emit(localDataUpdated)
    } else {
        val localData = getResult { readLocalData() }
        if (localData is ResultState.Success && localData.data.isNotEmpty()) {
            emit(localData)
        } else {
            emit(ResultState.Error((remoteData as ResultState.Error).throwable))
        }
    }
}