package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.entities.VendorInfo

data class VendorInfoDto(
    val vendorName: String,
    val vendorLogoUrl: String= "",
    val isProAccount: Boolean = false
) : FirestoreDto<VendorInfo>() {
    override fun map(): VendorInfo =
        VendorInfo(
            vendorName = vendorName,
            vendorLogoUrl = vendorLogoUrl,
            isProAccount = isProAccount
        )

    override fun mapDocumentFields(): HashMap<String, Any> =
        hashMapOf(
            "vendorname" to vendorName,
            "vendorLogoUrl" to vendorLogoUrl,
            "isProAccount" to isProAccount
        )
}