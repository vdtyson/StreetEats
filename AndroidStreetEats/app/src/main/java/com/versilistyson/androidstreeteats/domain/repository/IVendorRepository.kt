package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.BusinessInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure


import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

interface IVendorRepository {
    suspend fun updateVendorInfo(uid: String, updatedBusinessInfo: BusinessInfoDto): Either<Failure, Boolean>
    suspend fun getVendorInfo(uid: String): Either<Failure, BusinessInfo>
    suspend fun createNewVendorAccount(uid: String, businessInfo: BusinessInfoDto): Either<Failure, Boolean>
}