package com.versilistyson.androidstreeteats.presentation

import android.app.Application
import com.versilistyson.androidstreeteats.presentation.di.component.DaggerAppComponent

class StreetEatsApplication: Application() {
    val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }

}