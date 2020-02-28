package com.versilistyson.androidstreeteats.domain.exception.feature_failure

import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.versilistyson.androidstreeteats.domain.exception.Failure

sealed class FireAuthFailure(val e: FirebaseAuthException) : Failure.FeatureFailure(e) {
    data class AuthFailure(val e: FirebaseAuthException) : Failure.FeatureFailure(e)
    data class InvalidCredentialsFailure(val e: FirebaseAuthInvalidCredentialsException) : Failure.FeatureFailure(e)
    data class EmailFailure(val e: FirebaseAuthEmailException) : Failure.FeatureFailure(e)
}
//data class FireAuthFailure(val e: FirebaseAuthException) : Failure.FeatureFailure(e)
