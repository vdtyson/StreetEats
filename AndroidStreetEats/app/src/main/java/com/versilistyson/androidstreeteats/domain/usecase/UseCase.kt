package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import kotlinx.coroutines.*
import org.checkerframework.checker.guieffect.qual.UI

abstract class UseCase<out Type, in Params> where Type: Any? {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Either<Failure, Type>) -> Unit = {}
        ) {
        val backgroundJob = scope.async(dispatcher) { run(params) }
        scope.launch {
            onResult(
                backgroundJob.await()
            )
        }
    }

    class NoParams
}