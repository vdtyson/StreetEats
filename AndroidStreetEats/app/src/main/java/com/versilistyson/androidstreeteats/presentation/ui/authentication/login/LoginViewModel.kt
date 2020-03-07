package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.SignInWithEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
sealed class LoginState {
    object SuccessfulLogin: LoginState()
    data class FailedLogin(val failure: Failure): LoginState()
    object Loading: LoginState()
}
class LoginViewModel
@Inject constructor(
    private val signInWithEmail: SignInWithEmail
) : ViewModel() {

    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _loginState: MutableLiveData<LoginState> by lazy {
       MutableLiveData<LoginState>()
    }

    val loginState: LiveData<LoginState>
        get() = _loginState


    private val isEmailAndPasswordFilled = {
        !email.value.isNullOrBlank() && !password.value.isNullOrBlank()
    }


    fun onLogin() {
            _loginState.value = LoginState.Loading
            signInWithEmail(viewModelScope, params = SignInWithEmail.Params(email.value!!, password.value!!)) {
                it.fold(::handleFailure, ::handleFireAuthSuccess)
            }
        }

    private fun handleFireAuthSuccess(authResult: AuthResult) {
        _loginState.value = LoginState.SuccessfulLogin
    }


    private fun handleFailure(
        failure: Failure
    ) {
        _loginState.value = LoginState.FailedLogin(failure)
    }
}