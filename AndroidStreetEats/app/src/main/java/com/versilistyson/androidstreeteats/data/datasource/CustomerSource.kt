package com.versilistyson.androidstreeteats.data.datasource

import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.domain.datasource.ICustomerSource

class CustomerSource(streetEatsService: StreetEatsService) : ICustomerSource,
    FirestoreDataSource(streetEatsService, "customers") {


    override suspend fun writeNewCustomerAccount(uid: String, customerInfo: CustomerInfoDto) =
        writeDocumentAndMerge(baseCollectionReference, uid, customerInfo.mapDocumentFields())

    override suspend fun updateCustomerInfo(uid: String, updatedCustomerInfo: CustomerInfoDto) =
        updateDocument(baseCollectionReference, uid, updatedCustomerInfo.mapDocumentFields())

    override suspend fun fetchCustomerInfo(uid: String) =
        fetchDocumentSnapshot(baseCollectionReference, uid)
}