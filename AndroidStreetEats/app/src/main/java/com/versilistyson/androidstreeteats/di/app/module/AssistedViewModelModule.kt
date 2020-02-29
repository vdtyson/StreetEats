package com.versilistyson.androidstreeteats.di.app.module


import com.squareup.inject.assisted.AssistedInject
import com.squareup.inject.assisted.dagger2.AssistedModule
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import dagger.Module


@AssistedModule
@Module(includes = [AssistedInject_AssistedViewModelModule::class])
abstract class AssistedViewModelModule

/*
LoginViewModel_Factory::class,SignupViewModel_Factory::class,MainActivity_MembersInjector::class*/
