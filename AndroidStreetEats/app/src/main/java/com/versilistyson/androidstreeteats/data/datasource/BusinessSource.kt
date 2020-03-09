package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.db.FirestoreService
import com.versilistyson.androidstreeteats.data.firebase.models.BusinessInfoDto
import com.versilistyson.androidstreeteats.domain.datasource.IVendorSource
import javax.inject.Inject

class BusinessSource
@Inject constructor(firestoreService: FirestoreService) : FirestoreDataSource(firestoreService, "businesses"), IVendorSource {

    override suspend fun fetchVendorInfo(uid: String): Task<DocumentSnapshot> =
        fetchDocumentSnapshot(baseCollectionReference, uid)

    override suspend fun writeNewVendorAccount(uid: String, businessInfo: BusinessInfoDto): Task<Void> =
        writeDocumentAndMerge(baseCollectionReference, uid, businessInfo.mapDocumentFields())

    override suspend fun updateVendorInfo(uid: String, updatedBusinessInfo: BusinessInfoDto): Task<Void> =
        updateDocument(baseCollectionReference, uid, updatedBusinessInfo.mapDocumentFields())
}