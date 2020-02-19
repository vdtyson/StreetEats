package com.versilistyson.androidstreeteats.data.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.data.util.setDocumentAndMerge
import com.versilistyson.androidstreeteats.data.util.updateDocument
import com.versilistyson.androidstreeteats.domain.datasource.IVendorSource

class VendorSource(
    private val vendorCollectionRef: CollectionReference
) : IVendorSource {

    override suspend fun fetchVendorInfo(uid: String): Task<DocumentSnapshot> =
        vendorCollectionRef.document(uid).get()

    override suspend fun writeNewVendorAccount(uid: String, vendorInfo: VendorInfoDto): Task<Void> =
        vendorCollectionRef.document(uid).setDocumentAndMerge(vendorInfo.mapDocumentFields())

    override suspend fun updateVendorInfo(uid: String, updatedVendorInfo: VendorInfoDto): Task<Void> =
        vendorCollectionRef.document(uid).updateDocument(updatedVendorInfo.mapDocumentFields())
}