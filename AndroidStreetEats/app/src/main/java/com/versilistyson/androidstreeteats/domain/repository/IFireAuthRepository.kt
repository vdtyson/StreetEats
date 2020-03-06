package com.versilistyson.androidstreeteats.domain.repository

import com.google.firebase.auth.FirebaseAuth

interface IFireAuthRepository {
    val fireAuthInstance: FirebaseAuth
}