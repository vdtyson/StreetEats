package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.versilistyson.androidstreeteats.data.firebase.models.Account
import com.versilistyson.androidstreeteats.data.firebase.models.User
import com.versilistyson.androidstreeteats.data.util.FirestorePaths.ACCOUNT_COLLECTION
import com.versilistyson.androidstreeteats.data.util.FirestorePaths.BUSINESS_ACCOUNTS_DOC
import com.versilistyson.androidstreeteats.data.util.FirestorePaths.FOODIE_ACCOUNTS_DOC
import com.versilistyson.androidstreeteats.data.util.FirestorePaths.USER_COLLECTION
import com.versilistyson.androidstreeteats.data.util.FirestorePaths.WORKER_ACCOUNTS_DOC
import com.versilistyson.androidstreeteats.domain.datasource.authentication.IAuthenticationSource
import kotlinx.coroutines.tasks.await

class AuthenticationSource(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : IAuthenticationSource {

    private val userCollectionRef = db.collection(USER_COLLECTION)
    private val accountCollectionRef = db.collection(ACCOUNT_COLLECTION)

    override suspend fun writeNewAccount(account: Account): Task<DocumentReference> {
        return when(account) {
            is Account.Foodie -> {
                accountCollectionRef.document(FOODIE_ACCOUNTS_DOC).collection(account.key).add(account.mappedData)
            }
            is Account.Worker -> {
                accountCollectionRef.document(WORKER_ACCOUNTS_DOC).collection(account.key).add(account.mappedData)
            }
            is Account.Business -> {
                accountCollectionRef.document(BUSINESS_ACCOUNTS_DOC).collection(account.key).add(account.mappedData)
            }
        }
    }

    override suspend fun writeNewUser(newUser: User): Task<Void> {
        val newUserDocRef = userCollectionRef.document(newUser.key)
        return newUserDocRef.set(newUser.mappedData)
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> =
        auth.createUserWithEmailAndPassword(email, password)

    override suspend fun signInWitheEmailAndPassword(email: String, password: String): Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)

    override suspend fun sendPasswordResetEmail(email: String) =
        auth.sendPasswordResetEmail(email)

    override suspend fun signOutUser() =
        auth.signOut()

    override suspend fun getSignedInUser() =
        auth.currentUser
}

