package com.versilistyson.androidstreeteats.data.firebase.auth

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class FirebaseAuthService
@Inject constructor(
    override val authInstance: FirebaseAuth,
    override val phoneAuthProviderInstance: PhoneAuthProvider
) : IFirebaseAuthService