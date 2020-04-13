package com.versilistyson.androidstreeteats.data.firebase.models


import com.google.firebase.firestore.IgnoreExtraProperties
import com.versilistyson.androidstreeteats.domain.entities.UserInfo

@IgnoreExtraProperties
data class UserInfoDto(
    val accountType: String = "",
    val email: String = "",
    val phone: String = "",
    val accountSetupCompletionLevel: Int = 0 // 0 -- not setup up at all ; 1 -- Important parts filled out but still info to fill ; 2 -- setup complete
) : FirestoreDto<UserInfo>() {

    override fun map(): UserInfo {
        val accountType = when(accountType) {
            AccountType.BUSINESS.name -> AccountType.BUSINESS
            AccountType.CUSTOMER.name -> AccountType.CUSTOMER
            else -> {AccountType.CUSTOMER}
        }
       return  UserInfo(
            accountType = accountType,
            email = email,
            phone = phone,
           accountSetupCompletionLevel = accountSetupCompletionLevel
        )
    }


    override fun mapDocumentFields(): HashMap<String, Any?> =
        hashMapOf(
            "accountType" to accountType,
            "email" to email,
            "phone" to phone
        )


}