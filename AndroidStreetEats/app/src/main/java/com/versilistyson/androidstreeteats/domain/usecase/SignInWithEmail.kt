package com.versilistyson.androidstreeteats.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class SignInWithEmail
@Inject constructor(
    private val authenticationRepository: IAuthenticationRepository
) : UseCase<FirebaseUser, SignInWithEmail.Params>() {

    data class Params(val email: String, val password: String)

    override suspend fun run(params: Params): Either<Failure, FirebaseUser> =
        authenticationRepository.signInAccountWithEmailAndPassword(params.email, params.password)
}