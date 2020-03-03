package com.versilistyson.androidstreeteats.di

import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.versilistyson.androidstreeteats.di.app.component.AppComponent
import com.versilistyson.androidstreeteats.di.app.component.DaggerAppComponent
import com.versilistyson.androidstreeteats.di.util.InjectorProvider
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity


class StreetEatsApplication : Application(), InjectorProvider {
    private val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
    override val component: AppComponent
        get() = appComponent
}


