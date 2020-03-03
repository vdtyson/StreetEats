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
    val accountType: AccountType = AccountType.CUSTOMER,
    val email: String = "",
    val phone: String = ""
) : Entity<UserInfoDto>() {
    override fun map(): UserInfoDto =
        UserInfoDto(
            accountType = accountType,
            email = email,
            phone = phone
        )
}

data class BusinessInfo(
    val ownerFirstName: String = "",
    val ownerLastName: String = "",
    val vendorName: String = "",
    val mainCity: String = "",
    val vendorLogoUrl: String = "",
    val isProAccount: Boolean = false
) : Entity<BusinessInfoDto>() {

    override fun map(): BusinessInfoDto =
        BusinessInfoDto(
            ownerFirstName = ownerFirstName,
            ownerLastName = ownerLastName,
            vendorName = vendorName,
            mainCity = mainCity,
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
