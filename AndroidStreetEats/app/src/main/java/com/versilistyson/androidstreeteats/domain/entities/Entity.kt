package com.versilistyson.androidstreeteats.domain.entities

import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.business.BusinessInfoDto
import com.versilistyson.androidstreeteats.domain.common.Mappable

/*interface EmptyEntity<T> {
    fun empty() : T
}*/
sealed class Entity<T>:
    Mappable<T>

data class UserInfo(
    val accountType: AccountType = AccountType.CUSTOMER,
    val email: String = "",
    val phone: String = "",
    val accountSetupCompletionLevel: Int = 0 // 0 -- not setup up at all ; 1 -- Important parts filled out but still info to fill ; 2 -- setup complete
) : Entity<UserInfoDto>() {
    override fun map(): UserInfoDto =
        UserInfoDto(
            accountType = accountType.name,
            email = email,
            phone = phone,
            accountSetupCompletionLevel = accountSetupCompletionLevel
        )
}

data class BusinessInfo(
    val ownerFirstName: String = "",
    val ownerLastName: String = "",
    val businessName: String = "",
    val businessPhone: String = "",
    val businessEmail: String = "",
    val businessLogoUrl: String = "",
    val adminAccessCode: String = "",
    val isProAccount: Boolean = false,
    val requestAccessCodeOnLogin: Boolean = false
) : Entity<BusinessInfoDto>() {

    override fun map(): BusinessInfoDto =
        BusinessInfoDto(
            ownerFirstName = ownerFirstName,
            ownerLastName = ownerLastName,
            businessName = businessName,
            businessPhone = businessPhone,
            businessEmail = businessEmail,
            businessLogoUrl = businessLogoUrl,
            adminAccessCode = adminAccessCode,
            isProAccount = isProAccount,
            requestAccessCodeOnLogin = requestAccessCodeOnLogin
        )
}

data class CustomerInfo(
    val userName: String = ""
) : Entity<CustomerInfoDto>() {
    override fun map(): CustomerInfoDto =
        CustomerInfoDto(
            userName
        )
}

//
