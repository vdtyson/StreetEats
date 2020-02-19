package com.versilistyson.androidstreeteats.data.datasource

import com.google.firebase.firestore.CollectionReference
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.util.setDocumentAndMerge
import com.versilistyson.androidstreeteats.domain.datasource.ICustomerSource

class CustomerSource(
    private val customerCollectionRef: CollectionReference
) : ICustomerSource {

    override suspend fun writeNewCustomerAccount(uid: String, customerInfoDto: CustomerInfoDto) =
        customerCollectionRef.document(uid).setDocumentAndMerge(customerInfoDto.mapDocumentFields())

    override suspend fun fetchCustomerInfo(uid: String) =
        customerCollectionRef.document(uid).get()
}