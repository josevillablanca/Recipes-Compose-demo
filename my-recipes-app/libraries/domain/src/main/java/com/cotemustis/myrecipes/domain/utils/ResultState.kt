package com.cotemustis.myrecipes.domain.utils

sealed class ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val throwable: Throwable) : ResultState<Nothing>()
}

suspend fun <T> getResult(invoke: suspend () -> T): ResultState<T> {
    return runCatching {
        ResultState.Success(invoke())
    }.getOrElse {
        ResultState.Error(it)
    }
}