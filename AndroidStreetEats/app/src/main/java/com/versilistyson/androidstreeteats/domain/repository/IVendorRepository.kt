package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure


import com.versilistyson.androidstreeteats.domain.entities.VendorInfo

interface IVendorRepository {
    suspend fun updateVendorInfo(uid: String, updatedVendorInfo: VendorInfoDto): Either<Failure, Boolean>
    suspend fun getVendorInfo(uid: String): Either<Failure, VendorInfo>
    suspend fun createNewVendorAccount(uid: String, vendorInfo: VendorInfoDto): Either<Failure, Boolean>
}