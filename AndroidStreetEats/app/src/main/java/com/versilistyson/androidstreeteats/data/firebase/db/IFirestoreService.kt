package com.versilistyson.androidstreeteats.data.firebase.db

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

interface IFirestoreService {
    val instance: FirebaseFirestore
}