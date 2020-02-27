package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure

interface IUserRepository {
    suspend fun getUserInfo(uid: String) : Either<Failure, UserInfo>
    suspend fun addNewUser(uid: String, user: UserInfoDto) : Either<Failure, Boolean>
    suspend fun updateUserInfo(uid: String, user: UserInfoDto): Either<Failure, Boolean>
}