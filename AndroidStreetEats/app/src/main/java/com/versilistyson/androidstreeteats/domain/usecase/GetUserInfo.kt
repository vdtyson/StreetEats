package com.versilistyson.androidstreeteats.domain.usecase

import com.versilistyson.androidstreeteats.data.repository.UserRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure

class GetUserInfo(private val userRepository: UserRepository): UseCase<UserInfo, GetUserInfo.Params>() {

    data class Params(val uid: String)

    override suspend fun run(params: Params): Either<Failure, UserInfo> =
        userRepository.getUserInfo(params.uid)
}