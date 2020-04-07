package com.versilistyson.androidstreeteats.data.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.toObject
import com.versilistyson.androidstreeteats.data.datasource.UserSource
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.util.awaitTask
import com.versilistyson.androidstreeteats.data.util.taskCompletionRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FirestoreFailure
import com.versilistyson.androidstreeteats.domain.repository.IUserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepository
@Inject constructor(private val source: UserSource) : IUserRepository {

    override suspend fun fetchUserInfo(uid: String): Either<Failure, UserInfo> {
        return try {
            val snapshot = source.fetchUserInfo(uid).awaitTask()
            val mappedDto = snapshot.toObject<UserInfoDto>()
            Either.Right(mappedDto!!.map())
        } catch (e: IOException) {
            Either.Left(Failure.ServerError(e))
        } catch (e: FirebaseFirestoreException) {
            Either.Left(FirestoreFailure(e))
        }
    }

    override suspend fun writeUserInfo(uid: String, user: UserInfoDto): Either<Failure, Boolean> =
        source.writeNewUser(uid, user).taskCompletionRequest()

    override suspend fun updateUserInfo(uid: String, user: UserInfoDto): Either<Failure, Boolean> =
        source.updateUserInfo(uid, user).taskCompletionRequest()


}