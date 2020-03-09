package com.versilistyson.androidstreeteats.data.firebase.auth

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthProvider

interface IFirebaseAuthService {
    val authInstance: FirebaseAuth
    val phoneAuthProviderInstance: PhoneAuthProvider
}