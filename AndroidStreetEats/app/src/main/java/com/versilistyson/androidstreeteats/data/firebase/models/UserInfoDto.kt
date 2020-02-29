package com.versilistyson.androidstreeteats.data.firebase.models


import com.versilistyson.androidstreeteats.domain.entities.UserInfo

data class UserInfoDto(
    val firstName: String,
    val lastName: String,
    val accountType: AccountType,
    val email: String,
    val phone: String=""
) : FirestoreDto<UserInfo>() {

    override fun map(): UserInfo =
        UserInfo(
           firstName = firstName,
            lastName = lastName,
            accountType = accountType,
            email = email,
            phone = phone
        )

    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "accountType" to accountType.name,
            "email" to email,
            "phone" to phone
        )


}