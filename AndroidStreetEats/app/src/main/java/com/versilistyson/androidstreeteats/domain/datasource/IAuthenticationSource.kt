package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface IAuthenticationSource: BaseDataSource {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult>

    suspend fun signInWitheEmailAndPassword(email: String, password: String): Task<AuthResult>
    suspend fun sendPasswordResetEmail(email: String): Task<Void>
    suspend fun signOutUser()
    suspend fun getSignedInUser(): FirebaseUser?
    fun getFireAuthInstance(): FirebaseAuth
}