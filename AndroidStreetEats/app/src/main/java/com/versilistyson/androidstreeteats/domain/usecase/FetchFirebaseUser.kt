package com.versilistyson.androidstreeteats.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.data.repository.AuthenticationRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

class FetchFirebaseUser
@Inject constructor(private val authenticationRepository: AuthenticationRepository): UseCase<FirebaseUser?, UseCase.NoParams>() {
    override suspend fun run(params: NoParams): Either<Failure, FirebaseUser?> {
        val firebaseUser = authenticationRepository.fetchSignedInUser()
        return Either.Right(firebaseUser)
    }
}