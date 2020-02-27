package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.VendorRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.VendorInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

//TODO: Create usecase for creating vendor account
class CreateVendorAccount
@Inject constructor(private val vendorRepository: VendorRepository) : UseCase<Boolean, CreateVendorAccount.Params>() {
    data class Params(val uid: String, val vendorInfo: VendorInfo)

    override suspend fun run(params: Params): Either<Failure, Boolean> =
        vendorRepository.createNewVendorAccount(params.uid, params.vendorInfo.map())
}