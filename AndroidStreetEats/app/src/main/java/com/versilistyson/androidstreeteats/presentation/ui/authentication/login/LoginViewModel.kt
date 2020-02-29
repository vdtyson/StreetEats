package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
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
    val uid: String = "",
    val loggedInUserAccountType: AccountType? = null,
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
    private val signInWithEmail: SignInWithEmail,
    private val getUserInfo: GetUserInfo
) : BaseViewModel<LoginPageState>(initialPageState) {


    @AssistedInject.Factory
    interface Factory {
        fun create(initialPageState: LoginPageState): LoginViewModel
    }

    val email: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    val password: MutableLiveData<String?> by lazy {
        MutableLiveData<String?>()
    }

    private fun isValidEmailAndPassword(): Boolean =
        email.value != null || password.value != null


    fun emailSignIn(email: String, password: String) =
        viewModelScope.launch {
            setLoadingState(true)
            launch(Dispatchers.IO) {
                signInWithEmail(this, SignInWithEmail.Params(email, password)) { authResult ->
                    when (authResult) {
                        is Either.Right ->
                            handleFireAuthSuccess(this, authResult.right)
                        is Either.Left ->
                            handleLoginFailure(authResult.left)
                    }
                }
            }
        }

    private fun handleFireAuthSuccess(scope: CoroutineScope, authResult: AuthResult) {
        val uid = authResult.user!!.uid
        setState {
            copy(isLoginSuccessful = true, uid = uid)
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