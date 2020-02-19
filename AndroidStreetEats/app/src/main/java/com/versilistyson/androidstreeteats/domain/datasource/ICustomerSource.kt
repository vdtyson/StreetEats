package com.versilistyson.androidstreeteats.domain.datasource

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto

interface ICustomerSource {
    suspend fun writeNewCustomerAccount(uid: String, customerInfoDto: CustomerInfoDto): Task<Void>
    suspend fun fetchCustomerInfo(uid: String): Task<DocumentSnapshot>
}