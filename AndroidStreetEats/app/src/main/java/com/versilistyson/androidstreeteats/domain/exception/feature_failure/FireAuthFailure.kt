package com.versilistyson.androidstreeteats.domain.exception.feature_failure

import com.google.firebase.auth.*
import com.versilistyson.androidstreeteats.domain.exception.Failure
import java.lang.Exception

sealed class FireAuthFailure(private val fireAuthException: FirebaseAuthException) :
    Failure.FeatureFailure(fireAuthException) {

    object FormNotFilled: FeatureFailure()
    object PhoneVerificationFailed: FeatureFailure()
    object NoFirebaseUser: FeatureFailure(Exception("No Firebase user"))
    // Generic Auth Failure
    data class Other(val e: FirebaseAuthException) : FireAuthFailure(e)

    // Failed authentication
    data class InvalidCredentials(val e: FirebaseAuthInvalidCredentialsException) :
        Failure.FeatureFailure(e)

    // Operation on a FirebaseUser couldn't be completed due to a conflict with existing user
    data class ExistingUserConflict(val e: FirebaseAuthUserCollisionException) : FireAuthFailure(e)

    // Failure when sending an email (i.e password reset email)
    data class EmailNotSent(val e: FirebaseAuthEmailException) : FireAuthFailure(e)

    // Thrown on security sensitive operations on a FirebaseUser instance that require the user to have signed in recently, when the requirement isn't met
    data class CredentialsExpired(val e: FirebaseAuthRecentLoginRequiredException) : FireAuthFailure(e)
}
//data class FireAuthFailure(val e: FirebaseAuthException) : Failure.FeatureFailure(e)
