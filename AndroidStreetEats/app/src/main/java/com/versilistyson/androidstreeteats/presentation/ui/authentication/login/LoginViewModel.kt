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
    val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    val _isLoginSuccessful: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoginSuccessful: LiveData<Boolean>
        get() = _isLoginSuccessful

    val _failure: MutableLiveData<Failure> by lazy {
        MutableLiveData<Failure>()
    }
    val failure: LiveData<Failure>
        get() = _failure

    private val isEmailAndPasswordFilled = {
        !email.value.isNullOrBlank() && !password.value.isNullOrBlank()
    }


    fun onLogin() {
            _isLoading.value = true
            signInWithEmail(viewModelScope, params = SignInWithEmail.Params(email.value!!, password.value!!)) {
                it.fold(::handleFailure, ::handleFireAuthSuccess)
            }
        }

    private fun handleFireAuthSuccess(authResult: AuthResult) {
        _isLoading.value = false
        _isLoginSuccessful.value = true
    }


    fun handleFailure(
        failure: Failure
    ) {
        _isLoading.value = false
        _failure.value = failure
    }
}