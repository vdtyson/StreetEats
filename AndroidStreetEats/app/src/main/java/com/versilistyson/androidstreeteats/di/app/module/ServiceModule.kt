package com.versilistyson.androidstreeteats.di.app.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.versilistyson.androidstreeteats.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {

    @ApplicationScope
    @Provides
    fun provideStreetEatsService(firestore: FirebaseFirestore, firebaseAuth: FirebaseAuth) =
        StreetEatsService(firestore, firebaseAuth)

}