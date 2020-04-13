package com.versilistyson.androidstreeteats.domain.exception

//https://github.com/GlueHome/common-android/blob/master/domain/src/main/kotlin/com/gluehome/common/domain/exceptions/Failure.kt

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure(val exception: Exception = Exception("Failure")) {

    data class NetworkConnection(val e: Exception = Exception("Network Connection")): Failure(e)
    data class ServerError(val e: Exception = Exception("Server Error")): Failure(e)

    /** * Extend this class for feature specific failures.*/
    open class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)

    override fun equals(other: Any?): Boolean {
        return other is Failure
    }

    override fun hashCode(): Int {
        return exception.hashCode()
    }
}