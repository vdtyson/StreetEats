package com.versilistyson.androidstreeteats.domain.usecase

import com.google.firebase.auth.AuthResult
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class CreateUserWithEmail
@Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
) : UseCase<AuthResult, CreateUserWithEmail.Params>() {

    override suspend fun run(params: Params): Either<Failure, AuthResult> =
        authenticationRepo.createUserWithEmail(params.email, params.password)

    data class Params(
        val email: String,
        val password: String
    )
}