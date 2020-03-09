package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.versilistyson.androidstreeteats.data.firebase.auth.FirebaseAuthService
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.datasource.IAuthenticationSource
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthenticationSource
@Inject constructor(firebaseAuthService: FirebaseAuthService) : IAuthenticationSource {

    private val authInstance = firebaseAuthService.authInstance
    private val phoneAuthProviderInstance = firebaseAuthService.phoneAuthProviderInstance

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

    override fun getFireAuthInstance(): FirebaseAuth =
        authInstance

}

