package com.versilistyson.androidstreeteats.di.app.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object FirebaseModule {
    @Singleton
    @Provides @JvmStatic
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

    @Singleton
    @Provides @JvmStatic
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()
}