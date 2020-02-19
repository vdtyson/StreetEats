package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.VendorInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.common.FirestoreResult

import com.versilistyson.androidstreeteats.domain.entities.Entity
import com.versilistyson.androidstreeteats.domain.entities.VendorInfo

interface IVendorRepository {
    suspend fun getVendorInfo(uid: String): Either<Failure, VendorInfo>
    suspend fun createNewVendorAccount(uid: String, vendorInfoDto: VendorInfoDto): Either<Failure, Boolean>
}