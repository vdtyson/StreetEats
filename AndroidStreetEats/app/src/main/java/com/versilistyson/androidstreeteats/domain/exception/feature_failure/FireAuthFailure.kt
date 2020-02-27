package com.versilistyson.androidstreeteats.domain.exception.feature_failure

import com.google.firebase.auth.FirebaseAuthException
import com.versilistyson.androidstreeteats.domain.exception.Failure

data class FireAuthFailure(val e: FirebaseAuthException) : Failure.FeatureFailure(e)