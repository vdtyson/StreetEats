package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.datasource.BusinessInfoSource
import com.versilistyson.androidstreeteats.data.firebase.models.business.BusinessInfoDto
import com.versilistyson.androidstreeteats.data.util.objectFetchRequest
import com.versilistyson.androidstreeteats.data.util.taskCompletionRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

import com.versilistyson.androidstreeteats.domain.repository.IBusinessRepository
import javax.inject.Inject

class BusinessRepository
@Inject constructor(private val source: BusinessInfoSource) : IBusinessRepository {

    override suspend fun updateBusinessInfo(
        uid: String,
        updatedBusinessInfo: BusinessInfoDto
    ): Either<Failure, Boolean> =
        source.updateVendorInfo(uid, updatedBusinessInfo).taskCompletionRequest()

    override suspend fun getBusinessInfo(uid: String): Either<Failure, BusinessInfo> =
        source.fetchVendorInfo(uid).objectFetchRequest<BusinessInfoDto, BusinessInfo>(BusinessInfo())

    override suspend fun writeBusinessInfo(
        uid: String,
        businessInfo: BusinessInfoDto
    ): Either<Failure, Boolean> =
        source.writeNewVendorAccount(uid, businessInfo).taskCompletionRequest()

}