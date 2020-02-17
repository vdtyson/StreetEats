package com.versilistyson.androidstreeteats.domain.datasource.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.versilistyson.androidstreeteats.data.firebase.models.Account
import com.versilistyson.androidstreeteats.data.firebase.models.User
import com.versilistyson.androidstreeteats.domain.datasource.BaseDataSource

interface IAuthenticationSource: BaseDataSource {
    suspend fun writeNewAccount(account: Account): Task<DocumentReference>
    suspend fun writeNewUser(newUser: User): Task<Void>
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult>

    suspend fun signInWitheEmailAndPassword(email: String, password: String): Task<AuthResult>
    suspend fun sendPasswordResetEmail(email: String): Task<Void>
    suspend fun signOutUser()
    suspend fun getSignedInUser(): FirebaseUser?
}