package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.versilistyson.androidstreeteats.data.firebase.auth.FirebaseAuthService
import com.versilistyson.androidstreeteats.domain.datasource.IAuthenticationSource
import javax.inject.Inject

class AuthenticationSource
@Inject constructor(firebaseAuthService: FirebaseAuthService) : IAuthenticationSource {

    private val authInstance = firebaseAuthService.instance

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> =
        authInstance.createUserWithEmailAndPassword(email, password)

    override suspend fun signInWitheEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> =
        authInstance.signInWithEmailAndPassword(email, password)

    override suspend fun sendPasswordResetEmail(email: String) =
        authInstance.sendPasswordResetEmail(email)

    override suspend fun signOutUser() =
        authInstance.signOut()

    override suspend fun getSignedInUser() =
        authInstance.currentUser
}

