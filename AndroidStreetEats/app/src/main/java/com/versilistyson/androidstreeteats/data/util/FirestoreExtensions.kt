package com.versilistyson.androidstreeteats.data.util

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.SetOptions
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Updates the document at the given [DocumentReference] (receiver) with a set of new values specified
 * in a map with the fields (Strings of names of the fields to be updated) and the new values of any type
 * supported by firestore as the map's value. Returns a completable which completes if the
 * operation is successful or calls onError otherwise.
 *
 * @param updatedValues [Map] of field names (keys) and updated values (values)
 */
fun DocumentReference.updateDocument(updatedValues: Map<String, Any?>): Task<Void> =
    update(updatedValues)
// SetOptions.merge() only replaces the values specified in updatedValues
fun DocumentReference.setDocumentAndMerge(updatedValues: Map<String, Any?>): Task<Void> =
    set(updatedValues, SetOptions.merge())
