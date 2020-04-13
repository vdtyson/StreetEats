package com.versilistyson.androidstreeteats.data.util

import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun <T> Task<T>.awaitTask(): T =
    suspendCoroutine { continuation ->
        this
            .addOnSuccessListener { result ->
                continuation.resume(result)
            }
            .addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
    }

suspend fun Task<Void>.awaitTaskCompletion(): Boolean =
    suspendCoroutine { continuation ->
        this
            .addOnSuccessListener {
                continuation.resume(true)
            }
            .addOnFailureListener { e ->
                continuation.resumeWithException(e)
            }
    }
