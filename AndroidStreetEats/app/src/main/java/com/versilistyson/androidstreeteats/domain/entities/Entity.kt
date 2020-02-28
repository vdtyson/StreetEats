package com.versilistyson.androidstreeteats.domain.entities

import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.BusinessInfoDto
import com.versilistyson.androidstreeteats.domain.common.Mappable

/*interface EmptyEntity<T> {
    fun empty() : T
}*/
sealed class Entity<T>:
    Mappable<T>

data class UserInfo(
    val firstName: String = "",
    val lastName: String = "",
    val accountType: AccountType,
    val email: String = "",
    val phone: String = ""
) : Entity<UserInfoDto>() {
    override fun map(): UserInfoDto =
        UserInfoDto(
            firstName = firstName,
            lastName = lastName,
            accountType = accountType,
            email = email,
            phone = phone
        )
}

data class BusinessInfo(
    val vendorName: String = "",
    val vendorLogoUrl: String = "",
    val isProAccount: Boolean = false
) : Entity<BusinessInfoDto>() {

    override fun map(): BusinessInfoDto =
        BusinessInfoDto(
            vendorName = vendorName,
            vendorLogoUrl = vendorLogoUrl,
            isProAccount = isProAccount
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
