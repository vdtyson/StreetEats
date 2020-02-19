package com.versilistyson.androidstreeteats.domain.usecase

import com.google.android.gms.common.Feature
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import javax.inject.Inject

class CreateUserWithEmail
@Inject constructor(
    private val authenticationRepo: IAuthenticationRepository
) : UseCase<FirebaseUser, CreateUserWithEmail.Params>() {

    override suspend fun run(params: Params): Either<Failure, FirebaseUser> =
        authenticationRepo.createUserWithEmailAndPassword(params.email, params.password)

    data class Params(
        val email: String,
        val password: String
    )
}