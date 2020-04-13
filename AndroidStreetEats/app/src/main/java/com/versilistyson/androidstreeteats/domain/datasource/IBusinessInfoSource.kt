package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.business.BusinessInfoDto

interface IBusinessInfoSource: BaseDataSource {
    suspend fun fetchVendorInfo(uid: String): Task<DocumentSnapshot>
    suspend fun writeNewVendorAccount(uid: String, businessInfo: BusinessInfoDto): Task<Void>
    suspend fun updateVendorInfo(uid: String, updatedBusinessInfo: BusinessInfoDto): Task<Void>
}