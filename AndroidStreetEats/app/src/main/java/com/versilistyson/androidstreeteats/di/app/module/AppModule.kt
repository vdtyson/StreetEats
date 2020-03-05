package com.versilistyson.androidstreeteats.di.app.module

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object AppModule {

    @Singleton
    @Provides @JvmStatic
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Singleton
    @Provides @JvmStatic
    fun provideApplication(application: Application): Application =
        application

}