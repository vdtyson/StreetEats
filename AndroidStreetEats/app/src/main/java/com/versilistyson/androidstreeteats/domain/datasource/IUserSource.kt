package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto

interface IUserSource: BaseDataSource {
    suspend fun writeNewUser(uid: String, user: UserInfoDto): Task<Void>
    suspend fun fetchUserInfo(uid: String): Task<DocumentSnapshot>
    suspend fun updateUserInfo(uid: String, updatedUser: UserInfoDto): Task<Void>
}