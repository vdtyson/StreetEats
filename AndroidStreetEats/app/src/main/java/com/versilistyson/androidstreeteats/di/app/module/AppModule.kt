package com.versilistyson.androidstreeteats.di.app.module

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.versilistyson.androidstreeteats.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    @ApplicationScope
    fun provideApplication(application: Application): Application =
        application

    @Provides
    @ApplicationScope
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

    @Provides
    @ApplicationScope
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()
}