package com.versilistyson.androidstreeteats.di.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.versilistyson.androidstreeteats.di.StreetEatsApplication
import com.versilistyson.androidstreeteats.di.app.component.AppComponent

interface InjectorProvider {
    val component: AppComponent
}

