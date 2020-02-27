package com.versilistyson.androidstreeteats

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.versilistyson.androidstreeteats.di.app.component.DaggerAppComponent
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity


class StreetEatsApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}