package com.versilistyson.androidstreeteats.domain.exception.feature_failure

import com.google.firebase.firestore.FirebaseFirestoreException
import com.versilistyson.androidstreeteats.domain.exception.Failure

data class FirestoreTransactionFailure(val e: FirebaseFirestoreException) : Failure.FeatureFailure(e)