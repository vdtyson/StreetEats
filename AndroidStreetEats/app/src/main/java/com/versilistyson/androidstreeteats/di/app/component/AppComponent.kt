package com.versilistyson.androidstreeteats.di.app.component

import android.app.Application
import com.versilistyson.androidstreeteats.di.app.module.*
import com.versilistyson.androidstreeteats.presentation.ui.MainActivity
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginFragment
import com.versilistyson.androidstreeteats.presentation.ui.authentication.signup.SignupFragment
import com.versilistyson.androidstreeteats.presentation.ui.business.account.BusinessDashboardFragment
import com.versilistyson.androidstreeteats.presentation.ui.business.settings.BusinessSettingsFragment
import com.versilistyson.androidstreeteats.presentation.ui.map.MapFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,FirebaseModule::class,AssistedInjectModule::class, ViewModelModule::class, MapBoxModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application) : AppComponent
    }

    fun inject(target: MainActivity)
    fun inject(target: LoginFragment)
    fun inject(target: SignupFragment)
    fun inject(target: MapFragment)
    fun inject(target: BusinessSettingsFragment)
    fun inject(target: BusinessDashboardFragment)
}