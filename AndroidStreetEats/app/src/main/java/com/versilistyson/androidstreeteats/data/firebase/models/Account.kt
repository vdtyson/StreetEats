package com.versilistyson.androidstreeteats.data.firebase.models

sealed class Account: FirestoreDto {

    abstract override val key: String
    abstract override val mappedData: HashMap<String, Any?>
    enum class Type(name: String)  {
        WORKER("worker"),
        BUSINESS("business"),
        FOODIE("foodie")
    }
    data class Foodie(
        val id: String
    ): Account() {

        override val key: String = id

        override val mappedData: HashMap<String, Any?> =
            hashMapOf(
            )
    }

    data class Worker(
        val id: String,
        val businessId: String // Business id related to the vendor the worker works for
    ) : Account() {
        override val key: String = id
        override val mappedData: HashMap<String, Any?> =
            hashMapOf(
                "businessId" to businessId
            )
    }

    data class Business(
        val id: String, // Should be same as User Id
        val businessName: String,
        val isProAccount: Boolean = false
    ) : Account() {
        override val key: String = id
        override val mappedData: HashMap<String, Any?> =
            hashMapOf(
                "businessName" to businessName,
                "isProAccount" to isProAccount
            )
    }
}