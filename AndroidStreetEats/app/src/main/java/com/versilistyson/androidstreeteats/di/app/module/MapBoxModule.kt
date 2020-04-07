package com.versilistyson.androidstreeteats.di.app.module

import android.app.Application
import android.content.Context
import android.location.LocationProvider
import com.mapbox.android.core.location.LocationEngine
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.versilistyson.androidstreeteats.R
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
object MapBoxModule {

    private const val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L // 1 second
    private const val MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 10 // 10 seconds

    @Singleton
    @Provides @JvmStatic
    fun provideLocationEngine(context: Context): LocationEngine =
        LocationEngineProvider.getBestLocationEngine(context)

    @Singleton
    @Provides @JvmStatic
    fun provideLocationEngineRequest(): LocationEngineRequest =
        LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(MAX_WAIT_TIME)
            .build()

}
