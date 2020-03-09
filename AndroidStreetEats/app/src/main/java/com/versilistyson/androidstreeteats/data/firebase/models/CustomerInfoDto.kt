package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo

data class CustomerInfoDto(
    val userName: String
) : FirestoreDto<CustomerInfo>() {
    override fun map(): CustomerInfo =
        CustomerInfo(userName)

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf("userName" to userName)
}