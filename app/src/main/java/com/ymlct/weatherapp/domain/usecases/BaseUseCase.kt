package com.ymlct.weatherapp.domain.usecases

import com.ymlct.weatherapp.domain.repository.Failure
import com.ymlct.weatherapp.domain.repository.RepoResponse
import com.ymlct.weatherapp.domain.utils.log
import kotlinx.coroutines.*

abstract class BaseUseCase<in Params, out Type> where Type : Any {

    abstract suspend fun run(params: Params): RepoResponse<Type, Failure>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (RepoResponse<Type, Failure>) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            log("UseCase: enter method invoke() in ${Thread.currentThread()}")

            val deferred = async(Dispatchers.IO) {
                run(params)
            }

            onResult(deferred.await())

            log("UseCase: exit method invoke() in ${Thread.currentThread()}")
        }
    }

    class None
}
