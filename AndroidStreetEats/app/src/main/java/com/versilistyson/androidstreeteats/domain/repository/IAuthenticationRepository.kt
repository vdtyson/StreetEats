package com.versilistyson.androidstreeteats.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.data.firebase.models.Account
import com.versilistyson.androidstreeteats.data.firebase.models.User
import com.versilistyson.androidstreeteats.domain.common.FirebaseResult

interface IAuthenticationRepository: BaseRepository {
    suspend fun createUserWithEmailAndPassword(user: User, password: String): FirebaseResult<Any>

    suspend fun createAccount(business: Account.Business): FirebaseResult<Any>
    suspend fun createAccount(foodie: Account.Foodie): FirebaseResult<Any>
    suspend fun createAccount(worker: Account.Worker): FirebaseResult<Any>

    suspend fun signInAccountWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseResult<FirebaseUser?>

    suspend fun sendPasswordResetEmail(email: String)
    suspend fun fetchSignedInUser()
    suspend fun signOut()
}