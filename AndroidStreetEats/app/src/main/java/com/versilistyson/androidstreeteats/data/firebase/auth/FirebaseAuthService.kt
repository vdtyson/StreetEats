package com.versilistyson.androidstreeteats.data.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthService
@Inject constructor(override val instance: FirebaseAuth) : IFirebaseAuthService