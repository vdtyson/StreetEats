package com.versilistyson.androidstreeteats.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.versilistyson.androidstreeteats.data.firebase.auth.FirebaseAuthService
import com.versilistyson.androidstreeteats.domain.repository.IFireAuthRepository
import javax.inject.Inject

class FireAuthRepository
@Inject constructor(private val firebaseAuthService: FirebaseAuthService) : IFireAuthRepository {
    override val fireAuthInstance: FirebaseAuth
        get() = firebaseAuthService.instance
}