package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Type, in Params> where Type: Any? {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Either<Failure, Type>) -> Unit = {}
        ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch {
            onResult(
                backgroundJob.await()
            )
        }
    }
}