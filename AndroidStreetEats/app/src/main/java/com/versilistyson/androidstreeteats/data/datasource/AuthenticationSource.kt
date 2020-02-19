package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.versilistyson.androidstreeteats.domain.datasource.IAuthenticationSource

class AuthenticationSource(
    private val auth: FirebaseAuth
) : IAuthenticationSource {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> =
        auth.createUserWithEmailAndPassword(email, password)

    override suspend fun signInWitheEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)

    override suspend fun sendPasswordResetEmail(email: String) =
        auth.sendPasswordResetEmail(email)

    override suspend fun signOutUser() =
        auth.signOut()

    override suspend fun getSignedInUser() =
        auth.currentUser
}

