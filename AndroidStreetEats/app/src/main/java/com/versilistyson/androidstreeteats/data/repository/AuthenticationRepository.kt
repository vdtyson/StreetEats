package com.versilistyson.androidstreeteats.data.repository


import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.data.datasource.AuthenticationSource
import com.versilistyson.androidstreeteats.data.util.fireAuthRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import javax.inject.Inject


class AuthenticationRepository
@Inject constructor(private val source: AuthenticationSource) : IAuthenticationRepository {

    override suspend fun createUserWithEmail(
        email: String,
        password: String
    ): Either<Failure, AuthResult> =
        source.createUserWithEmailAndPassword(email, password).fireAuthRequest()

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Either<Failure, AuthResult> =
        source.signInWitheEmailAndPassword(email, password).fireAuthRequest()


    override suspend fun fetchSignedInUser(): FirebaseUser? = source.getSignedInUser()

    override suspend fun signOut() = source.signOutUser()
}



