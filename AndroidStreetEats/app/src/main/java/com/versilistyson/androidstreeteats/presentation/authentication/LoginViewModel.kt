package com.versilistyson.androidstreeteats.presentation.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import com.versilistyson.androidstreeteats.domain.usecase.SignInWithEmail
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val signInWithEmail: SignInWithEmail
) : ViewModel() {

    private val _user: MutableLiveData<FirebaseUser> by lazy {
        MutableLiveData<FirebaseUser>()
    }

    val user: LiveData<FirebaseUser>
    get() = _user

    fun signInWithEmail(email: String, password: String) =
        signInWithEmail.invoke(
            viewModelScope,
            SignInWithEmail.Params(email, password)
        ) {result ->
            result.either(::handleAuthFailure, ::handAuthSuccess)
        }

    private fun handleAuthFailure(failure: Failure) {
        TODO()
    }
    private fun handAuthSuccess(user: FirebaseUser) {
        _user.postValue(user)
    }
}