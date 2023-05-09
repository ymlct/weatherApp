package com.ymlct.weatherapp.domain.repository

sealed class RepoResponse<out R, out E> {
    data class Succeeded<out R>(val result: R) : RepoResponse<R, Nothing>()
    data class Failed<out E>(val error: E) : RepoResponse<Nothing, E>()

    fun execute(onSuccess: (R) -> Any, onFailure: (E) -> Any) =
        when (this) {
            is Succeeded -> onSuccess(result)
            is Failed -> onFailure(error)
        }
}