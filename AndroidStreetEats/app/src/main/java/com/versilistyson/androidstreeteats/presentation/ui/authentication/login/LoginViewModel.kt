package com.versilistyson.androidstreeteats.presentation.ui.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.haroldadmin.vector.VectorState
import com.haroldadmin.vector.VectorViewModel
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.GetUserInfo
import com.versilistyson.androidstreeteats.domain.usecase.SignInWithEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val userAccountType: String? = null,
    val loginProgress: LoginProgress = LoginProgress.Idle,
    val failure: Failure? = null
) : VectorState {
    enum class LoginProgress {
        Idle,
        Loading,
        Failure,
        Success
    }
}

class LoginViewModel
@Inject constructor(
    initialState: LoginState,
    private val signInWithEmail: SignInWithEmail,
    private val getUserInfo: GetUserInfo
) : VectorViewModel<LoginState>(initialState) {

    fun loginWithEmail(email: String, password: String) =
        viewModelScope.launch {
            setState {
                copy(loginProgress = LoginState.LoginProgress.Loading)
            }
            launch {
                signInWithEmail(this, SignInWithEmail.Params(email, password)) {
                    when(it) {
                        is Either.Left ->
                            handleAuthFailure(it.left)
                        is Either.Right ->
                            handAuthSuccess(this, it.right)
                    }
                }
            }
        }

    private fun handleAuthFailure(failure: Failure) {
        setState{
            copy(loginProgress = LoginState.LoginProgress.Failure, failure = failure)
        }
    }

    private fun handAuthSuccess(scope: CoroutineScope, authResult: AuthResult) {
        val uid = authResult.user!!.uid
        getUserInfo(scope, GetUserInfo.Params(uid)) {
            when(it) {
                is Either.Left ->
                    handleAuthFailure(it.left)
                is Either.Right ->
                    setState {
                        copy(userAccountType = it.right.accountType)
                    }
            }
        }
    }
}