package com.versilistyson.androidstreeteats.di.app.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.di.util.ViewModelKey
import com.versilistyson.androidstreeteats.presentation.ui.AppViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.login.LoginViewModel
import com.versilistyson.androidstreeteats.presentation.ui.authentication.signup.SignUpViewModel
import com.versilistyson.androidstreeteats.presentation.ui.business.account.BusinessDashboardViewModel
import com.versilistyson.androidstreeteats.presentation.ui.business.settings.BusinessSettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel::class)
    abstract fun bindMainSharedViewModel(appViewModel: AppViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindCustomerSignupViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BusinessSettingsViewModel::class)
    abstract fun bindBusinessSettingsViewModel(businessSettingsViewModel: BusinessSettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BusinessDashboardViewModel::class)
    abstract fun bindBusinessDashboardViewModel(businessDashboardViewModel: BusinessDashboardViewModel): ViewModel
}