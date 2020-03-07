package com.versilistyson.androidstreeteats.di.app.module

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.versilistyson.androidstreeteats.R
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

    @Singleton
    @Provides @JvmStatic
    fun providePhoneAuthProvider(): PhoneAuthProvider =
        PhoneAuthProvider.getInstance()

    @Singleton
    @Provides @JvmStatic
    fun provideGoogleSignInOptions(context: Context): GoogleSignInOptions {
        val webClientId = context.getString(R.string.default_web_client_id)
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId) //TODO: Get resource web_client_id
            .requestEmail()
            .build()
    }


    @Singleton
    @Provides @JvmStatic
    fun provideGoogleSignInClient(context: Context, googleSignInOptions: GoogleSignInOptions): GoogleSignInClient =
        GoogleSignIn.getClient(context, googleSignInOptions)

}