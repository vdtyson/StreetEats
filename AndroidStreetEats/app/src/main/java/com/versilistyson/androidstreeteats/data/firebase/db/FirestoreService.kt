package com.versilistyson.androidstreeteats.data.firebase.db

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreService
@Inject constructor(override val instance: FirebaseFirestore) : IFirestoreService