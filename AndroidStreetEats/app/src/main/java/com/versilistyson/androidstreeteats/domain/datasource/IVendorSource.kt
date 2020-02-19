package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto

interface IVendorSource {
    suspend fun fetchVendorInfo(uid: String): Task<DocumentSnapshot>
    suspend fun writeNewVendorAccount(uid: String, vendorInfo: VendorInfoDto): Task<Void>
    suspend fun updateVendorInfo(uid: String, updatedVendorInfo: VendorInfoDto): Task<Void>
}