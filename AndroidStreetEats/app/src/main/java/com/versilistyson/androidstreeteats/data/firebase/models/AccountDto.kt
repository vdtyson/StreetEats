package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.data.util.Mappable

import kotlin.collections.HashMap

enum class AccountType(name: String) {
    WORKER("worker"),
    BUSINESS("business"),
    FOODIE("foodie")
}