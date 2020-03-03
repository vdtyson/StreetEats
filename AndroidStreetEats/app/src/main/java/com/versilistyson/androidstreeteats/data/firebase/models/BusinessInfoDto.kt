package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

data class BusinessInfoDto(
    val ownerFirstName: String,
    val ownerLastName: String,
    val vendorName: String,
    val mainCity: String,
    val vendorLogoUrl: String= "",
    val isProAccount: Boolean = false
) : FirestoreDto<BusinessInfo>() {
    override fun map(): BusinessInfo =
        BusinessInfo(
            vendorName = vendorName,
            vendorLogoUrl = vendorLogoUrl,
            isProAccount = isProAccount
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "ownerFirstName" to ownerFirstName,
            "ownerLastName" to ownerLastName,
            "vendorname" to vendorName,
            "mainCity" to mainCity,
            "vendorLogoUrl" to vendorLogoUrl,
            "isProAccount" to isProAccount
        )
}