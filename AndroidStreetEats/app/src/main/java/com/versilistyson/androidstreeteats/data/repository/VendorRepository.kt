package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.data.util.objectFetchRequest
import com.versilistyson.androidstreeteats.data.util.taskCompletionRequest
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.datasource.IVendorSource
import com.versilistyson.androidstreeteats.domain.entities.VendorInfo

import com.versilistyson.androidstreeteats.domain.repository.IVendorRepository
import javax.inject.Inject

class VendorRepository
@Inject constructor(private val source: IVendorSource) : IVendorRepository {

    override suspend fun updateVendorInfo(
        uid: String,
        updatedVendorInfo: VendorInfoDto
    ): Either<Failure, Boolean> =
        source.updateVendorInfo(uid, updatedVendorInfo).taskCompletionRequest()

    override suspend fun getVendorInfo(uid: String): Either<Failure, VendorInfo> =
        source.fetchVendorInfo(uid).objectFetchRequest<VendorInfoDto, VendorInfo>(VendorInfo())

    override suspend fun createNewVendorAccount(
        uid: String,
        vendorInfo: VendorInfoDto
    ): Either<Failure, Boolean> =
        source.writeNewVendorAccount(uid, vendorInfo).taskCompletionRequest()

}