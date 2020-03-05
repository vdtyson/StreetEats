package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure.*
import com.versilistyson.androidstreeteats.domain.usecase.SignInWithEmail
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.PageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class LoginPageState(
    val isLoginSuccessful: Boolean = false
) : PageState<LoginPageState>() {
    override fun resetToDefaultState(): LoginPageState =
        LoginPageState()
}

class LoginViewModel
@AssistedInject constructor(
    @Assisted initialPageState: LoginPageState,
    private val signInWithEmail: SignInWithEmail
) : BaseViewModel<LoginPageState>(initialPageState) {


    @AssistedInject.Factory
    interface Factory {
        fun create(initialPageState: LoginPageState): LoginViewModel
    }


    val email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val isEmailAndPasswordFilled = {
        !email.value.isNullOrBlank() && !password.value.isNullOrBlank()
    }


    fun onLogin() =
        viewModelScope.launch(Dispatchers.IO) {
            if (isEmailAndPasswordFilled()) {
                setLoadingState(true) {
                    it.resetToDefaultState()
                }
                signInWithEmail(
                    this,
                    SignInWithEmail.Params(email.value!!, password.value!!)
                ) { authResult ->
                    authResult.fold({ handleFailure(it) }, ::handleFireAuthSuccess)
                }
            } else {
                setErrorState(true, "Fill all boxes") {
                    it.copy(
                        isLoginSuccessful = false
                    )
                }
            }
        }

    private fun handleFireAuthSuccess(authResult: AuthResult) {
        val firebaseUser = authResult.user!!.uid
        setState {
            copy(isLoginSuccessful = true)
        }
    }


    override fun handleFailure(
        failure: Failure,
        extraStateChanges: ((LoginPageState) -> LoginPageState)?
    ) {
        when (failure) {
            is InvalidCredentials ->
                setErrorState(
                    true,
                    "Invalid Credentials.",
                    failure = failure,
                    extraStateChanges = extraStateChanges
                )
        }
        super.handleFailure(failure, extraStateChanges)
    }
}