package com.versilistyson.androidstreeteats.di.app.module

import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
object AssistedInjectModule