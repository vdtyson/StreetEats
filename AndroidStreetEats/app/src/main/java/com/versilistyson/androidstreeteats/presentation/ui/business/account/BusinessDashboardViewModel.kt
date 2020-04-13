package com.versilistyson.androidstreeteats.presentation.ui.business.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.androidstreeteats.data.AppSharedPrefs
import com.versilistyson.androidstreeteats.domain.entities.BusinessInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.FetchBusinessInfo
import javax.inject.Inject

sealed class BusinessDashBoardState() {
    object AccountNotSetup: BusinessDashBoardState()
    data class Account(val businessName: String): BusinessDashBoardState()
}

class BusinessDashboardViewModel
@Inject constructor(
    private val fetchBusinessInfo: FetchBusinessInfo,
    private val appSharedPrefs: AppSharedPrefs
) : ViewModel() {

    private val _state: MutableLiveData<BusinessDashBoardState> by lazy {
        MutableLiveData<BusinessDashBoardState>()
    }
    val state: LiveData<BusinessDashBoardState>
    get() = _state

    fun getBusinessInfo() {
        val accountCompletionLevel = appSharedPrefs.fetchAccountSetupCompletionLevel()
        if(accountCompletionLevel == 0) {
            _state.value = BusinessDashBoardState.AccountNotSetup
        } else {
            val userid = appSharedPrefs.fetchUserId() ?: return
            fetchBusinessInfo(viewModelScope, FetchBusinessInfo.Params(userid)) {result ->
                result.fold(::handleFailure, ::handleSuccess)
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        TODO()
    }

    private fun handleSuccess(businessInfo: BusinessInfo) {
        _state.postValue(BusinessDashBoardState.Account(businessInfo.businessName))
    }
}