package com.versilistyson.androidstreeteats.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.versilistyson.androidstreeteats.data.firebase.auth.FirebaseAuthService
import com.versilistyson.androidstreeteats.domain.repository.IFireAuthRepository
import javax.inject.Inject

class FireAuthRepository
@Inject constructor(firebaseAuthService: FirebaseAuthService) : IFireAuthRepository {

    override val fireAuthInstance: FirebaseAuth =
        firebaseAuthService.authInstance

    override val phoneAuthProviderInstance: PhoneAuthProvider =
        firebaseAuthService.phoneAuthProviderInstance
}