package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo

interface ICustomerRepository {
    suspend fun createNewCustomerAccount(uid: String, customerInfo: CustomerInfoDto): Either<Failure, Boolean>
    suspend fun getCustomerAccountInfo(uid: String): Either<Failure, CustomerInfo>
}