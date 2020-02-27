package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.util.objectFetchRequest
import com.versilistyson.androidstreeteats.data.util.taskCompletionRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.datasource.IUserSource
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.repository.IUserRepository
import javax.inject.Inject

class UserRepository
@Inject constructor(private val source: IUserSource) : IUserRepository {

    override suspend fun getUserInfo(uid: String): Either<Failure, UserInfo> =
        source.fetchUserInfo(uid).objectFetchRequest<UserInfoDto, UserInfo>(UserInfo())

    override suspend fun addNewUser(uid: String, user: UserInfoDto): Either<Failure, Boolean> =
        source.writeNewUser(uid, user).taskCompletionRequest()

    override suspend fun updateUserInfo(uid: String, user: UserInfoDto): Either<Failure, Boolean> =
        source.updateUserInfo(uid, user).taskCompletionRequest()


}