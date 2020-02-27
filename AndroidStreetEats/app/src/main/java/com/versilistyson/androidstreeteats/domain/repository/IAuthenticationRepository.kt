package com.versilistyson.androidstreeteats.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure

interface IAuthenticationRepository: BaseRepository {

    suspend fun createUserWithEmail(email: String, password: String): Either<Failure, AuthResult>

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): Either<Failure, AuthResult>

    suspend fun fetchSignedInUser(): FirebaseUser?
    suspend fun signOut()
}