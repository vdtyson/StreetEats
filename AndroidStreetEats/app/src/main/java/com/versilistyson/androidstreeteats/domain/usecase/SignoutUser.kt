package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.AuthenticationRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

class SignoutUser
@Inject constructor(
    private val authenticationRepo: AuthenticationRepository
) : UseCase<Boolean, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): Either<Failure, Boolean> {
        return try {
            authenticationRepo.signOut()
            Either.Right(true)
        } catch (e: Exception) {
            // TODO: Needs to be changed
            Either.Left(Failure.ServerError(e))
        }
    }
}