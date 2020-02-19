package com.versilistyson.androidstreeteats.data.util

interface FirestoreDocument {
    fun mapDocumentFields() : HashMap<String, Any>
}