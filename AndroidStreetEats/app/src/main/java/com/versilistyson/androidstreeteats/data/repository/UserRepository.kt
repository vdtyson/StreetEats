package com.versilistyson.androidstreeteats.data.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.domain.common.FirestoreResult
import com.versilistyson.androidstreeteats.domain.datasource.IUserSource
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.repository.IUserRepository
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val source: IUserSource
) : IUserRepository {
    override suspend fun getUserInfo(uid: String): UserInfo? {
        val result = source.fetchUserInfo(uid).await()
        val mappedDto = result.toObject(UserInfoDto::class.java)
        return mappedDto?.map()
    }

    override suspend fun addNewUser(uid: String, user: UserInfoDto) {
        source.writeNewUser(uid, user).await()
    }

}