package com.versilistyson.androidstreeteats.data.repository

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestoreException
import com.versilistyson.androidstreeteats.data.firebase.models.Account
import com.versilistyson.androidstreeteats.data.firebase.models.User
import com.versilistyson.androidstreeteats.domain.common.FirebaseResult
import com.versilistyson.androidstreeteats.domain.datasource.authentication.IAuthenticationSource
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import kotlinx.coroutines.tasks.await

class AuthenticationRepository(private val source: IAuthenticationSource) :
    IAuthenticationRepository {


    override suspend fun createUserWithEmailAndPassword(
        user: User,
        password: String
    ): FirebaseResult<Any> {
        try {
            val authResult = source.createUserWithEmailAndPassword(user.email, password).await()
            val newUid = authResult.user?.uid ?: return FirebaseResult.FirebaseAuthError()
            val newUser = user.copy(uid = newUid)
            source.writeNewUser(newUser).await()
            return FirebaseResult.Data()
        } catch (e: FirebaseFirestoreException) {
            return FirebaseResult.FireStoreError(e)
        } catch (e: FirebaseAuthException) {
            return FirebaseResult.FirebaseAuthError(e)
        }
    }

    override suspend fun createAccount(business: Account.Business): FirebaseResult<Any> {
        return try {
            source.writeNewAccount(business).await()
            FirebaseResult.SuccessfulTransaction
        } catch (e: FirebaseFirestoreException) {
            return FirebaseResult.FireStoreError(e)
        }
    }

    override suspend fun createAccount(foodie: Account.Foodie): FirebaseResult<Any> {
        return try {
            source.writeNewAccount(foodie).await()
            FirebaseResult.SuccessfulTransaction
        } catch (e: FirebaseFirestoreException) {
            return FirebaseResult.FireStoreError(e)
        }
    }

    override suspend fun createAccount(worker: Account.Worker): FirebaseResult<Any> {
        return try {
            source.writeNewAccount(worker).await()
            FirebaseResult.SuccessfulTransaction
        } catch (e: FirebaseFirestoreException) {
            return FirebaseResult.FireStoreError(e)
        }
    }


    override suspend fun signInAccountWithEmailAndPassword(
        email: String,
        password: String
    ): FirebaseResult<FirebaseUser?> {
        return try {
            val signInResult = source.signInWitheEmailAndPassword(email, password).await()
            FirebaseResult.Data(signInResult.user)
        } catch (firebaseAuthException: FirebaseAuthException) {
            return FirebaseResult.FirebaseAuthError(firebaseAuthException)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetchSignedInUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun signOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}