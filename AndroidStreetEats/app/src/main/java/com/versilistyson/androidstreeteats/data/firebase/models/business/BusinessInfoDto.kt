package com.versilistyson.androidstreeteats.data.firebase.models.business

import com.versilistyson.androidstreeteats.data.firebase.models.FirestoreDto
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

data class BusinessInfoDto(
    val ownerFirstName: String,
    val ownerLastName: String,
    val businessName: String,
    val businessPhone: String,
    val businessEmail: String = "",
    val businessLogoUrl: String= "",
    val isProAccount: Boolean = false,
    val adminAccessCode: String = "",
    val requestAccessCodeOnLogin: Boolean = false
) : FirestoreDto<BusinessInfo>() {
    override fun map(): BusinessInfo =
        BusinessInfo(
            ownerFirstName = ownerFirstName,
            ownerLastName =  ownerLastName,
            businessName = businessName,
            businessPhone = businessPhone,
            businessEmail = businessEmail,
            businessLogoUrl = businessLogoUrl,
            isProAccount = isProAccount,
            adminAccessCode = adminAccessCode,
            requestAccessCodeOnLogin = requestAccessCodeOnLogin
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "ownerFirstName" to ownerFirstName,
            "ownerLastName" to ownerLastName,
            "businessName" to businessName,
            "businessPhone" to businessPhone,
            "businessEmail" to businessEmail,
            "businessLogoUrl" to businessLogoUrl,
            "isProAccount" to isProAccount,
            "adminAccessCode" to adminAccessCode,
            "requestAccessCodeOnLogin" to requestAccessCodeOnLogin
        )
}