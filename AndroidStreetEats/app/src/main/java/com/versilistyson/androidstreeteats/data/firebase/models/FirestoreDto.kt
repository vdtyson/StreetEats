package com.versilistyson.androidstreeteats.data.firebase.models

import com.google.firebase.firestore.FirebaseFirestore

interface FirestoreDto {
    val key: String
    val mappedData: HashMap<String, Any?>
}