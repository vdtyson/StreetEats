package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
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
    val loggedInUserAccountType: AccountType? = null,
    val isLoginSuccessful: Boolean = false,
    val errorType: ErrorType? = null,
    val isLoading: Boolean

) : PageState() {
    enum class ErrorType {
        SERVER,
        NO_CONNECTION,
        INVALID_CREDENTIALS
    }
}

class LoginViewModel(
    initialPageState: LoginPageState,
    private val signInWithEmail: SignInWithEmail,
    private val getUserInfo: GetUserInfo
) : BaseViewModel<LoginPageState>(initialPageState) {

    fun emailSignIn(email: String, password: String) =
        viewModelScope.launch {
            setLoadingState()
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
        getUserInfo(scope, GetUserInfo.Params(uid)) { firestoreResult ->
            when (firestoreResult) {

                is Either.Right -> {
                    val newUserInfo = firestoreResult.right
                    setState { copy(loggedInUserAccountType = newUserInfo.accountType, isLoginSuccessful = true)}
                }
                is Either.Left ->
                    handleLoginFailure(firestoreResult.left)
            }
        }
    }


    private fun handleLoginFailure(failure: Failure) {

        when (failure) {
            is Failure.NetworkConnection -> {
                setErrorState("No Network Connection.") {
                    setState {copy(errorType = LoginPageState.ErrorType.NO_CONNECTION)}
                }
            }
            is Failure.ServerError -> {
                val errorMessage = failure.e.message ?: ""
                setErrorState(errorMessage) {
                    setState { copy(errorType = LoginPageState.ErrorType.SERVER) }
                }
            }

            is FireAuthFailure -> {
                when(failure) {
                    is InvalidCredentialsFailure -> {
                        setErrorState("Invalid credentials.") {
                            setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }
                        }
                    }
                    else -> {
                        setErrorState("${failure.e.message}") {
                            setState { copy(errorType = LoginPageState.ErrorType.INVALID_CREDENTIALS) }
                        }
                    }
                }
            }

            is FirestoreFailure -> {
                val errorMessage = failure.e.message ?: ""
                setErrorState(errorMessage) {
                    setState { copy(errorType = LoginPageState.ErrorType.SERVER) }
                }
            }
        }
    }

}