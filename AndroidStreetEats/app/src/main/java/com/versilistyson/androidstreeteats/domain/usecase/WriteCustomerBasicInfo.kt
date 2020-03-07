package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.CustomerRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

//TODO: Create use case for creating customer account
class WriteCustomerBasicInfo
@Inject constructor(private val customerRepository: CustomerRepository) : UseCase<Boolean, WriteCustomerBasicInfo.Params>() {

    data class Params(val uid: String, val customerInfo: CustomerInfo)

    override suspend fun run(params: Params): Either<Failure, Boolean> =
        customerRepository.writeCustomerInfo(params.uid, params.customerInfo.map())

}