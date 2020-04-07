package com.versilistyson.androidstreeteats.domain.repository

import com.versilistyson.androidstreeteats.data.firebase.models.business.BusinessInfoDto
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure


import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo

interface IBusinessRepository {
    suspend fun updateBusinessInfo(uid: String, updatedBusinessInfo: BusinessInfoDto): Either<Failure, Boolean>
    suspend fun getBusinessInfo(uid: String): Either<Failure, BusinessInfo>
    suspend fun writeBusinessInfo(uid: String, businessInfo: BusinessInfoDto): Either<Failure, Boolean>
}