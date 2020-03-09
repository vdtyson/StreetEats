package com.versilistyson.androidstreeteats.presentation.ui.authentication.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import com.versilistyson.androidstreeteats.presentation.util.LiveEvent
import javax.inject.Inject
sealed class SignupEvent {
    data class FailedSignup(val failure: Failure): SignupEvent()
    object SuccessfulSignUp: SignupEvent()
}
data class SignUpState(val isLoading: Boolean = false, val accountType: AccountType = AccountType.CUSTOMER)

class SignUpViewModel
@Inject constructor(
    private val createUserWithEmail: CreateUserWithEmail
) : ViewModel() {


    val signupEvent: LiveEvent<SignupEvent> = LiveEvent()

    private val _signUpState: MutableLiveData<SignUpState> by lazy {
        MutableLiveData<SignUpState>(SignUpState())
    }

    val signUpState: LiveData<SignUpState>
        get() = _signUpState

    val isBusiness: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private fun isValidCredentials(): Boolean {
        return !email.value.isNullOrBlank()  && !password.value.isNullOrBlank()
    }

    fun onBusinessCheckChanged() {
        if(isBusiness.value!!) {
            _signUpState.value = signUpState.value!!.copy(accountType = AccountType.BUSINESS)
        } else {
            _signUpState.value = signUpState.value!!.copy(accountType = AccountType.CUSTOMER)
        }
    }

    fun onSignup() {
        if(isValidCredentials()) {
            _signUpState.value = signUpState.value!!.copy(isLoading = true)
            when(signUpState.value!!.accountType) {
                AccountType.BUSINESS -> {
                    createUserWithEmail(
                        viewModelScope,
                        CreateUserWithEmail.Params(
                            email.value!!,
                            password.value!!,
                            UserInfo(email = email.value!!, accountType = AccountType.BUSINESS)
                        )
                    ) {it.fold(::handleSignupFailure) {handleSignupSuccess()} }
                }
                AccountType.CUSTOMER -> {
                    createUserWithEmail(
                        viewModelScope,
                        CreateUserWithEmail.Params(
                            email.value!!,
                            password.value!!,
                            UserInfo(email = email.value!!, accountType = AccountType.CUSTOMER)
                        )
                    ) {it.fold(::handleSignupFailure) {handleSignupSuccess()} }
                }
            }

        } else {
            handleSignupFailure(FireAuthFailure.FormNotFilled)
        }
    }

    private fun handleSignupFailure(failure: Failure) {
        _signUpState.value = signUpState.value!!.copy(isLoading = false)
        signupEvent.value = SignupEvent.FailedSignup(failure)
    }
    private fun handleSignupSuccess() {
        _signUpState.value = signUpState.value!!.copy(isLoading = false)
        signupEvent.value = SignupEvent.SuccessfulSignUp
    }
}