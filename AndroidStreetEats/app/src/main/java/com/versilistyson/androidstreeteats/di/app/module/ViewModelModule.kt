package com.versilistyson.androidstreeteats.di.app.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.di.util.ViewModelKey
import com.versilistyson.androidstreeteats.presentation.ui.MainSharedViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.signup.CustomerSignupViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainSharedViewModel::class)
    abstract fun bindMainSharedViewModel(mainSharedViewModel: MainSharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CustomerSignupViewModel::class)
    abstract fun bindCustomerSignupViewModel(customerSignupViewModel: CustomerSignupViewModel): ViewModel
}