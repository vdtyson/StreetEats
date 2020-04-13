package com.versilistyson.androidstreeteats.di.app.module

import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/*
@Module
object NetworkingModule {

    private const val BASE_URL = "TODO" //TODO: Add base url

    @Singleton
    @Provides @JvmStatic
    fun provideOkHttpClient() =
        OkHttpClient()

    @Singleton
    @Provides @JvmStatic
    fun provideRequestHeaders() {
        TODO()
    }

    @Singleton
    @Provides @JvmStatic
    fun provideRequestInterceptor() {
        val sumn = HttpLoggingInterceptor()
    }

    @Singleton
    @Provides @JvmStatic
    fun provideRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

}*/
