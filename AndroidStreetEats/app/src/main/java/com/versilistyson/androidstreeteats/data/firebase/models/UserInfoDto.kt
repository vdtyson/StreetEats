package com.versilistyson.androidstreeteats.data.firebase.models


import com.versilistyson.androidstreeteats.domain.entities.UserInfo

data class UserInfoDto(
    val accountType: AccountType,
    val email: String,
    val phone: String="",
    val isAccountCreationComplete: Boolean = false
) : FirestoreDto<UserInfo>() {

    override fun map(): UserInfo =
        UserInfo(
            accountType = accountType,
            email = email,
            phone = phone,
            isAccountCreationComplete = false
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "accountType" to accountType.name,
            "email" to email,
            "phone" to phone,
            "isAccountCreationComplete" to isAccountCreationComplete
        )


}