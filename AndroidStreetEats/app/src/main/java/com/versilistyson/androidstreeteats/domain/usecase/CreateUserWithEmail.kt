package com.versilistyson.androidstreeteats.domain.usecase

import com.google.firebase.auth.AuthResult
import com.versilistyson.androidstreeteats.data.repository.AuthenticationRepository
import com.versilistyson.androidstreeteats.data.repository.UserRepository
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import javax.inject.Inject

class CreateUserWithEmail
@Inject constructor(
    private val authenticationRepo: AuthenticationRepository,
    private val userRepo: UserRepository
) : UseCase<AuthResult, CreateUserWithEmail.Params>() {

    override suspend fun run(params: Params): Either<Failure, AuthResult> {

        val authResult =
            authenticationRepo.createUserWithEmail(params.email, params.password)

        return when (authResult) {
            is Either.Right -> {
                val uid = authResult.right.user!!.uid
                when (val firestoreResult = userRepo.writeUserInfo(uid, params.userInfo.map())) {
                    is Either.Left ->
                        Either.Left(firestoreResult.left)
                    is Either.Right ->
                        Either.Right(authResult.right)
                }
            }
            is Either.Left -> {
                Either.Left(authResult.left)
            }
        }
    }


    data class Params(
        val email: String,
        val password: String,
        val userInfo: UserInfo
    )
}