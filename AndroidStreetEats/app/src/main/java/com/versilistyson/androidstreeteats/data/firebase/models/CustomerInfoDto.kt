package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo

data class CustomerInfoDto(
    val userName: String,
    val profilePictureUrl: String = ""
) : FirestoreDto<CustomerInfo>() {
    override fun map(): CustomerInfo =
        CustomerInfo(
            userName,
            profilePictureUrl
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "userName" to userName,
            "profilePictureUrl" to profilePictureUrl
        )
}