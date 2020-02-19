package com.versilistyson.androidstreeteats.data.firebase.models

import com.versilistyson.androidstreeteats.data.util.FirestoreDocument
import com.versilistyson.androidstreeteats.data.util.Mappable

abstract class FirestoreDto<T>: Mappable<T>, FirestoreDocument {
}