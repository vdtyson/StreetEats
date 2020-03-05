package com.versilistyson.androidstreeteats.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.versilistyson.androidstreeteats.domain.exception.Failure

fun <T: Any, L: LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L: LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))

fun Fragment.showToastMessage(message: String, duration: Int) {
    Toast.makeText(this.context, message, duration).show()
}
