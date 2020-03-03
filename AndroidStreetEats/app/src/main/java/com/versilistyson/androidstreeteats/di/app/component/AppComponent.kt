package com.versilistyson.androidstreeteats.di.app.component

import android.app.Application
import com.versilistyson.androidstreeteats.di.app.module.AssistedViewModelModule
import com.versilistyson.androidstreeteats.di.app.module.ContextModule
import com.versilistyson.androidstreeteats.di.app.module.FirebaseModule
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginFragment
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.signup.SignupViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class,FirebaseModule::class, AssistedViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

    val loginViewModelFactory: LoginViewModel.Factory
    val mainSharedViewModelFactory: MainSharedViewModel.Factory
    val signupViewModelFactory: SignupViewModel.Factory
}