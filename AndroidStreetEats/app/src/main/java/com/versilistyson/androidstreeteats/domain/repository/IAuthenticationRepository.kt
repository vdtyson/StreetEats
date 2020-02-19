package com.versilistyson.androidstreeteats.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure

interface IAuthenticationRepository: BaseRepository {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Either<Failure, FirebaseUser>

    suspend fun signInAccountWithEmailAndPassword(
        email: String,
        password: String
    ): Either<Failure, FirebaseUser>

    suspend fun fetchSignedInUser(): FirebaseUser?
    suspend fun signOut()
}