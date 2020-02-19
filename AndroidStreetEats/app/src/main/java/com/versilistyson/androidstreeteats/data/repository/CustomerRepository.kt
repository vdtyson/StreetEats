package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.util.awaitTask
import com.versilistyson.androidstreeteats.data.util.awaitTaskCompletion
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.datasource.ICustomerSource
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.repository.ICustomerRepository

class CustomerRepository(
    val source: ICustomerSource
) : ICustomerRepository {

    override suspend fun createNewCustomerAccount(
        uid: String,
        customerInfo: CustomerInfoDto
    ): Either<Failure, Boolean> =
        source.writeNewCustomerAccount(uid, customerInfo).taskCompletionRequest()

    override suspend fun getCustomerAccountInfo(uid: String): Either<Failure, CustomerInfo> =
        source.fetchCustomerInfo(uid).objectFetchRequest<CustomerInfoDto, CustomerInfo>()

}