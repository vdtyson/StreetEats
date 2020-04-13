package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto

interface ICustomerSource: BaseDataSource {
    suspend fun writeNewCustomerAccount(uid: String, customerInfo: CustomerInfoDto): Task<Void>
    suspend fun updateCustomerInfo(uid: String, updatedCustomerInfo: CustomerInfoDto): Task<Void>
    suspend fun fetchCustomerInfo(uid: String): Task<DocumentSnapshot>
}