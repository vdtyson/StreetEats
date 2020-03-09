package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.domain.common.Mappable

abstract class FirestoreDto<T>:
    Mappable<T> {
    abstract fun mapDocumentFields() : HashMap<String, Any?>
}