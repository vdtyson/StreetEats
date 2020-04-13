package com.versilistyson.androidstreeteats.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

interface IFireAuthRepository {
    val fireAuthInstance: FirebaseAuth
    val phoneAuthProviderInstance: PhoneAuthProvider
}