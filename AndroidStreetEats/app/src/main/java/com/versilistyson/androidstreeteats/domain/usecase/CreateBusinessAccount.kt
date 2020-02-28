package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.VendorRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

//TODO: Create usecase for creating vendor account
class CreateBusinessAccount
@Inject constructor(private val vendorRepository: VendorRepository) : UseCase<Boolean, CreateBusinessAccount.Params>() {
    data class Params(val uid: String, val businessInfo: BusinessInfo)

    override suspend fun run(params: Params): Either<Failure, Boolean> =
        vendorRepository.createNewVendorAccount(params.uid, params.businessInfo.map())
}