package com.versilistyson.androidstreeteats.domain.common

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.R
import com.google.firebase.firestore.FirebaseFirestoreException

interface Result<T>

sealed class FirebaseResult<T> : Result<T> {

    object SuccessfulTransaction : FirebaseResult<Any>()
    data class Data<T>(val obj: T? = null) : FirebaseResult<T>()
    data class FirebaseAuthError<T>(val firebaseAuthException: FirebaseAuthException? = null) :
        FirebaseResult<T>()
    data class FireStoreError<T>(val firebaseFirestoreException: FirebaseFirestoreException? = null) :
        FirebaseResult<T>()
}

