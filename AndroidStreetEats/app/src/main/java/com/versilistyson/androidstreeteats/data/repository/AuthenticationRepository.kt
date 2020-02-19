package com.versilistyson.androidstreeteats.data.repository


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.versilistyson.androidstreeteats.data.firebase.models.FirestoreDto
import com.versilistyson.androidstreeteats.data.util.awaitTask
import com.versilistyson.androidstreeteats.data.util.awaitTaskCompletion
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.datasource.IAuthenticationSource
import com.versilistyson.androidstreeteats.domain.entities.Entity
import com.versilistyson.androidstreeteats.domain.repository.IAuthenticationRepository
import javax.inject.Inject


class AuthenticationRepository
@Inject constructor(
    private val source: IAuthenticationSource
) : IAuthenticationRepository {

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Either<Failure, FirebaseUser> =
        source.createUserWithEmailAndPassword(email, password).firebaseAuthRequest()

    override suspend fun signInAccountWithEmailAndPassword(
        email: String,
        password: String
    ): Either<Failure, FirebaseUser> =
        source.signInWitheEmailAndPassword(email, password).firebaseAuthRequest()


    override suspend fun fetchSignedInUser(): FirebaseUser? = source.getSignedInUser()

    override suspend fun signOut() = source.signOutUser()


}

data class AuthenticationFailure(val authException: FirebaseAuthException) :
    Failure.FeatureFailure(authException)

suspend fun Task<AuthResult>.firebaseAuthRequest(): Either<Failure, FirebaseUser> {
    return try {
        val result = this.awaitTask()
        val user = result.user
        if (user == null) {
            Either.Left(Failure.ServerError())
        } else {
            Either.Right(user)
        }
    } catch (e: FirebaseAuthException) {
        Either.Left(AuthenticationFailure(e))
    }
}

// emptyDefault: R
suspend inline fun <reified T : FirestoreDto<R>, R> Task<DocumentSnapshot>.objectFetchRequest(): Either<Failure, R> {
    return try {
        val snapshot = this.awaitTask()
        val mappedDto =
            snapshot.toObject(T::class.java) ?: return Either.Left(Failure.ServerError())
        return Either.Right(mappedDto.map())
    } catch (e: FirebaseFirestoreException) {
        Either.Left(Failure.ServerError(e))
    }
}

suspend inline fun Task<Void>.taskCompletionRequest(): Either<Failure, Boolean> {
    return try {
        val result = this.awaitTaskCompletion()
        Either.Right(result)
    } catch (e: Exception) {
        Either.Left(Failure.ServerError(e))
    }
}
/*suspend fun taskCompletetionRequest(task: Task<Void>): Either<Failure, Boolean> {
    return try {
        val result = task.awaitTaskCompletion()
        Either.Right(result)
    } catch (e: Exception) {
        Either.Left(Failure.FailedTransaction(e))
    }
}*/
/*suspend fun <T: FirestoreDto<R>, R>userFetchRequest(task: Task<T>): Either<Failure, R> {
    return try {
        val result = task.awaitTask()
        Either.Right(result.map())
    } catch (exception: Exception) {
        Either.Left(Failure.ServerError(exception))
    }
}*/