package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.versilistyson.androidstreeteats.data.firebase.models.AccountType
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure.*
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FirestoreFailure
import com.versilistyson.androidstreeteats.domain.usecase.GetUserInfo
import com.versilistyson.androidstreeteats.domain.usecase.SignInWithEmail
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.PageState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class LoginPageState(
    val firebaseUser: FirebaseUser? = null,
    val isLoginSuccessful: Boolean = false,
    val errorType: ErrorType? = null
) : PageState() {
    enum class ErrorType {
        SERVER,
        NO_CONNECTION,
        INVALID_CREDENTIALS
    }
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
        email.value != null && password.value != null
    }


    fun onLogin() =
        viewModelScope.launch {
            if (isEmailAndPasswordFilled()) {
                setLoadingState(true)
                launch(Dispatchers.IO) {
                    signInWithEmail(this, SignInWithEmail.Params(email.value!!, password.value!!)) { authResult ->
                        when (authResult) {
                            is Either.Right ->
                                handleFireAuthSuccess(authResult.right)
                            is Either.Left ->
                                handleLoginFailure(authResult.left)
                        }
                    }
                }
            } else {
                setErrorState(true) {setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }}
            }
        }

    private fun handleFireAuthSuccess(authResult: AuthResult) {
        val firebaseUser = authResult.user
        setState {
            copy(isLoginSuccessful = true, firebaseUser = firebaseUser)
        }
    }


    private fun handleLoginFailure(failure: Failure) {

        when (failure) {
            is Failure.NetworkConnection -> {
                setErrorState(true, "No Network Connection") {
                    setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }
                }
            }
            is Failure.ServerError -> {
                val errorMessage = failure.e.message ?: ""
                setErrorState(true, errorMessage) {
                    setState { copy(errorType = LoginPageState.ErrorType.SERVER) }
                }
            }

            is FireAuthFailure -> {
                when (failure) {
                    is InvalidCredentialsFailure -> {
                        setErrorState(true, "Invalid credentials.") {
                            setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }
                        }
                    }
                    else -> {
                        setErrorState(true, "${failure.e.message}") {
                            setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }
                        }
                    }
                }
            }

            is FirestoreFailure -> {
                val errorMessage = failure.e.message ?: ""
                setErrorState(true, errorMessage) {
                    setState { copy(errorType = LoginPageState.ErrorType.SERVER) }
                }
            }
        }
    }

}