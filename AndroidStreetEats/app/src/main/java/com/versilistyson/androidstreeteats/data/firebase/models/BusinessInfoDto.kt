package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

data class BusinessInfoDto(
    val ownerFirstName: String,
    val ownerLastName: String,
    val businessName: String,
    val businessPhone: String,
    val businessEmail: String = "",
    val businessLogoUrl: String= "",
    val isProAccount: Boolean = false
) : FirestoreDto<BusinessInfo>() {
    override fun map(): BusinessInfo =
        BusinessInfo(
            businessName = businessName,
            vendorLogoUrl = businessLogoUrl,
            isProAccount = isProAccount
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "ownerFirstName" to ownerFirstName,
            "ownerLastName" to ownerLastName,
            "businessName" to businessName,
            "businessPhone" to businessPhone,
            "businessEmail" to businessEmail,
            "businessLogoUrl" to businessLogoUrl,
            "isProAccount" to isProAccount
        )
}