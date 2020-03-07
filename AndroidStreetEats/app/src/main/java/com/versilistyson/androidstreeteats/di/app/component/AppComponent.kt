package com.versilistyson.androidstreeteats.di.app.component

import android.app.Application
import com.versilistyson.androidstreeteats.di.app.module.AppModule
import com.versilistyson.androidstreeteats.di.app.module.AssistedInjectModule
import com.versilistyson.androidstreeteats.di.app.module.FirebaseModule
import com.versilistyson.androidstreeteats.di.app.module.ViewModelModule
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginFragment
import com.versilistyson.androidstreeteats.presentation.ui.authentication.signup.SignupFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,FirebaseModule::class,AssistedInjectModule::class, ViewModelModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

    fun inject(target: MainActivity)
    fun inject(target: LoginFragment)
    fun inject(target: SignupFragment)
}