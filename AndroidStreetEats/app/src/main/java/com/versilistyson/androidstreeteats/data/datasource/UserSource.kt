package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.domain.datasource.IUserSource

class UserSource(streetEatsService: StreetEatsService) : IUserSource,
    FirestoreDataSource(streetEatsService,"users") {


    override suspend fun writeNewUser(uid: String, user: UserInfoDto): Task<Void> =
        writeDocumentAndMerge(baseCollectionReference, uid, user.mapDocumentFields())

    override suspend fun fetchUserInfo(uid: String): Task<DocumentSnapshot> =
        fetchDocumentSnapshot(baseCollectionReference, uid)

    override suspend fun updateUserInfo(uid: String, updatedUser: UserInfoDto): Task<Void> =
        updateDocument(baseCollectionReference, uid, updatedUser.mapDocumentFields())
}