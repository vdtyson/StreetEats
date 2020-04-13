package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.BusinessRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

class FetchBusinessInfo
@Inject constructor(private val businessRepository: BusinessRepository):
    UseCase<BusinessInfo, FetchBusinessInfo.Params>() {


    override suspend fun run(params: Params): Either<Failure, BusinessInfo> =
        businessRepository.getBusinessInfo(params.uid)


    data class Params(val uid: String)
}