package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.domain.datasource.IVendorSource

class VendorSource(streetEatsService: StreetEatsService) : FirestoreDataSource(streetEatsService, "vendors"), IVendorSource {

    override suspend fun fetchVendorInfo(uid: String): Task<DocumentSnapshot> =
        fetchDocumentSnapshot(baseCollectionReference, uid)

    override suspend fun writeNewVendorAccount(uid: String, vendorInfo: VendorInfoDto): Task<Void> =
        writeDocumentAndMerge(baseCollectionReference, uid, vendorInfo.mapDocumentFields())

    override suspend fun updateVendorInfo(uid: String, updatedVendorInfo: VendorInfoDto): Task<Void> =
        updateDocument(baseCollectionReference, uid, updatedVendorInfo.mapDocumentFields())
}