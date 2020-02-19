package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.util.setDocumentAndMerge
import com.versilistyson.androidstreeteats.data.util.updateDocument
import com.versilistyson.androidstreeteats.domain.datasource.IUserSource

class UserSource(
    private val userCollectionRef: CollectionReference
) : IUserSource {
    override suspend fun writeNewUser(uid: String, user: UserInfoDto): Task<Void> =
        userCollectionRef.document(uid).setDocumentAndMerge(user.mapDocumentFields())

    override suspend fun fetchUserInfo(uid: String): Task<DocumentSnapshot> =
        userCollectionRef.document(uid).get()

    override suspend fun updateUserInfo(uid: String, updatedUser: UserInfoDto): Task<Void> =
        userCollectionRef.document(uid).updateDocument(updatedUser.mapDocumentFields())
}