package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.versilistyson.androidstreeteats.domain.entities.CustomerInfo
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.CreateCustomerAccount
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import javax.inject.Inject

sealed class CustomerSignupState {
    data class FailedSignup(val failure: Failure) : CustomerSignupState()
    object SuccessfulSignup : CustomerSignupState()
    object Loading : CustomerSignupState()
}


class CustomerSignupViewModel
@Inject constructor(
    private val createUserWithEmail: CreateUserWithEmail,
    private val createCustomerAccount: CreateCustomerAccount
) : ViewModel() {

    val _customerSignupState: MutableLiveData<CustomerSignupState> by lazy {
        MutableLiveData<CustomerSignupState>()
    }

    val customerSignupState: LiveData<CustomerSignupState>
        get() = _customerSignupState

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val username: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private fun isValidateCredentials(): Boolean {
        return !email.value.isNullOrBlank() && !username.value.isNullOrBlank() && !password.value.isNullOrBlank()
    }


    fun writeCustomerAccount() {
            createUserWithEmail(
                viewModelScope,
                CreateUserWithEmail.Params(
                    email.value!!,
                    password.value!!,
                    UserInfo(email = email.value!!)
                )
            ) {
                it.fold(::handleSignupFailure) { authResult ->
                    createCustomerAccount(
                        viewModelScope,
                        CreateCustomerAccount.Params(
                            authResult.user!!.uid, CustomerInfo(
                                username.value!!
                            )
                        )
                    ) { customerWriteResult ->
                        customerWriteResult.fold(::handleSignupFailure,{handleSignupSuccess()})
                    }
                }
            }
        }

    private fun handleSignupFailure(failure: Failure) {
        _customerSignupState.value = CustomerSignupState.FailedSignup(failure = failure)
    }
    private fun handleSignupSuccess() {
        _customerSignupState.value =CustomerSignupState.SuccessfulSignup
    }
}