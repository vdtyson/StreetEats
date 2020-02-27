package com.versilistyson.androidstreeteats.domain.usecase

import com.google.firebase.auth.AuthResult
import com.versilistyson.androidstreeteats.data.repository.AuthenticationRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

class SignInWithEmail
@Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : UseCase<AuthResult, SignInWithEmail.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun run(params: Params): Either<Failure, AuthResult> =
        authenticationRepository.signInWithEmail(params.email, params.password)
}