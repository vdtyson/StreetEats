package com.versilistyson.androidstreeteats.data.util

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.versilistyson.androidstreeteats.data.firebase.models.FirestoreDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure.*
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FirestoreFailure

import java.io.IOException

suspend inline fun <reified T : FirestoreDto<R>, R> Task<DocumentSnapshot>.objectFetchRequest(emptyDefault: R): Either<Failure, R> =
    try {
        val snapshot = this.awaitTask()
        when(val mappedDto = snapshot.toObject(T::class.java)) {
            null -> Either.Right(emptyDefault)
            else -> {
                Either.Right(mappedDto.map())
            }
        }
    } catch (e: IOException) {
        Either.Left(Failure.ServerError(e))
    } catch (e: FirebaseFirestoreException) {
        Either.Left(FirestoreFailure(e))
    }


suspend fun Task<Void>.taskCompletionRequest(): Either<Failure, Boolean> =
    try {
        val result = this.awaitTaskCompletion()
        Either.Right(result)
    } catch (e: IOException) {
        Either.Left(Failure.ServerError(e))
    } catch (e: FirebaseFirestoreException) {
        Either.Left(FirestoreFailure(e))
    }


suspend fun Task<AuthResult>.fireAuthRequest(): Either<Failure, AuthResult> =
    try {
        Either.Right(this.awaitTask())
    } catch (e: IOException) {
        Either.Left(Failure.ServerError(e))
    } catch (e: FirebaseAuthInvalidCredentialsException) {
        Either.Left(InvalidCredentialsFailure(e))
    } catch(e: FirebaseAuthEmailException) {
        Either.Left(EmailFailure(e))
    } catch (e: FirebaseAuthException) {
        Either.Left(AuthFailure(e))
    }

