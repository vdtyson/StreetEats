package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.datasource.CustomerSource
import com.versilistyson.androidstreeteats.data.firebase.models.CustomerInfoDto
import com.versilistyson.androidstreeteats.data.util.objectFetchRequest
import com.versilistyson.androidstreeteats.data.util.taskCompletionRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.datasource.ICustomerSource
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.repository.ICustomerRepository
import javax.inject.Inject

class CustomerRepository
@Inject constructor(private val source: CustomerSource) : ICustomerRepository {

    override suspend fun createNewCustomerAccount(
        uid: String,
        customerInfo: CustomerInfoDto
    ): Either<Failure, Boolean> =
        source.writeNewCustomerAccount(uid, customerInfo).taskCompletionRequest()

    override suspend fun getCustomerAccountInfo(uid: String): Either<Failure, CustomerInfo> =
        source.fetchCustomerInfo(uid).objectFetchRequest<CustomerInfoDto, CustomerInfo>(CustomerInfo())

}