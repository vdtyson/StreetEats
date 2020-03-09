package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.BusinessRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

//TODO: Create usecase for creating vendor account
class WriteBusinessBasicInfo
@Inject constructor(private val businessRepository: BusinessRepository) : UseCase<Boolean, WriteBusinessBasicInfo.Params>() {
    data class Params(val uid: String, val businessInfo: BusinessInfo)

    override suspend fun run(params: Params): Either<Failure, Boolean> =
        businessRepository.writeBusinessInfo(params.uid, params.businessInfo.map())
}