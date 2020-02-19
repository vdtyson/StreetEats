package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.domain.entities.UserInfo

interface IUserRepository {
    suspend fun getUserInfo(uid: String) : UserInfo?
    suspend fun addNewUser(uid: String, user: UserInfoDto)
}