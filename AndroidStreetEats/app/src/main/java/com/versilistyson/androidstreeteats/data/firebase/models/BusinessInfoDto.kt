package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

data class BusinessInfoDto(
    val vendorName: String,
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
            "vendorname" to vendorName,
            "vendorLogoUrl" to vendorLogoUrl,
            "isProAccount" to isProAccount
        )
}