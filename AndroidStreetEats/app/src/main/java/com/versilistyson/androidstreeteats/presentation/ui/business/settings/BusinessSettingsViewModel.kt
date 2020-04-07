package com.versilistyson.androidstreeteats.presentation.ui.business.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.androidstreeteats.domain.usecase.SignoutUser
import com.versilistyson.androidstreeteats.domain.usecase.UseCase
import javax.inject.Inject

class BusinessSettingsViewModel
@Inject constructor(val signoutUser: SignoutUser) : ViewModel() {
    fun onSignOut() {
        signoutUser(viewModelScope, UseCase.NoParams())
    }
}