package com.versilistyson.androidstreeteats.data.firebase.models

import com.google.firebase.firestore.FirebaseFirestore
data class User(
    val uid: String = "",
    val firstName: String,
    val lastName: String,
    val accountType: Account.Type,
    val email: String,
    val phone: String?
) : FirestoreDto {

    override val key: String = uid

    override val mappedData: HashMap<String, Any?> =
        hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "accountType" to accountType.name,
            "email" to email,
            "phone" to phone
        )
}