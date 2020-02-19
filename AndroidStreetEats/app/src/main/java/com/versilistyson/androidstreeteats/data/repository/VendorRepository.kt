package com.versilistyson.androidstreeteats.data.repository

import com.versilistyson.androidstreeteats.data.firebase.models.FirestoreDto
import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.data.util.awaitTask
import com.versilistyson.androidstreeteats.data.util.awaitTaskCompletion
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.datasource.IVendorSource
import com.versilistyson.androidstreeteats.domain.entities.VendorInfo

import com.versilistyson.androidstreeteats.domain.repository.IVendorRepository

class VendorRepository(private val source: IVendorSource) : IVendorRepository {

    override suspend fun getVendorInfo(uid: String): Either<Failure, VendorInfo> =
        source.fetchVendorInfo(uid).objectFetchRequest<VendorInfoDto, VendorInfo>()

    override suspend fun createNewVendorAccount(
        uid: String,
        vendorInfoDto: VendorInfoDto
    ): Either<Failure, Boolean> =
        source.writeNewVendorAccount(uid, vendorInfoDto).taskCompletionRequest()

}