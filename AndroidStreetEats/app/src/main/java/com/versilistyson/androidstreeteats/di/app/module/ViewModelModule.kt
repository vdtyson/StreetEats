package com.versilistyson.androidstreeteats.di.app.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.androidstreeteats.di.util.ViewModelProviderFactory
import com.versilistyson.androidstreeteats.di.util.ViewModelKey
import com.versilistyson.androidstreeteats.presentation.ui.SharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    internal abstract fun bindMainViewModel(mainActivityViewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}