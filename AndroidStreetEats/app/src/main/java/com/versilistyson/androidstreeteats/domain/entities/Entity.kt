package com.versilistyson.androidstreeteats.domain.entities

import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.UserInfoDto
import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.data.util.Mappable

/*interface EmptyEntity<T> {
    fun empty() : T
}*/
sealed class Entity<T>: Mappable<T> {
}
data class UserInfo(
    val firstName: String,
    val lastName: String,
    val accountType: String,
    val email: String,
    val phone: String = ""
) : Entity<UserInfoDto>() {
    override fun map(): UserInfoDto =
        UserInfoDto(
            firstName = firstName,
            lastName = lastName,
            accountType = AccountType.valueOf(accountType),
            email = email,
            phone = phone
        )
}

data class VendorInfo(
    val vendorName: String,
    val vendorLogoUrl: String = "",
    val isProAccount: Boolean = false
) : Entity<VendorInfoDto>() {
    override fun map(): VendorInfoDto =
        VendorInfoDto(
            vendorName = vendorName,
            vendorLogoUrl = vendorLogoUrl,
            isProAccount = isProAccount
        )
}

data class CustomerInfo(
    val userName: String,
    val profilePictureUrl: String = ""
) : Entity<CustomerInfoDto>() {
    override fun map(): CustomerInfoDto =
        CustomerInfoDto(
            userName,
            profilePictureUrl
        )
}

//
