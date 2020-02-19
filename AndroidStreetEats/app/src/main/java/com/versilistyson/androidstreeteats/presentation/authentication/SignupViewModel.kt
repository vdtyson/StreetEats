package com.versilistyson.androidstreeteats.presentation.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.common.Failure
import com.versilistyson.androidstreeteats.domain.usecase.CreateUserWithEmail
import javax.inject.Inject

class SignupViewModel
@Inject constructor(
    private val createUserWithEmail: CreateUserWithEmail
) : ViewModel() {
    private val _user: MutableLiveData<FirebaseUser> by lazy {
        MutableLiveData<FirebaseUser>()
    }

    val user: LiveData<FirebaseUser>
    get() = _user

    fun signUpWithEmail(email: String, password: String) =
        createUserWithEmail.invoke(
            viewModelScope,
            CreateUserWithEmail.Params(email, password)
        ) { result ->
            result.either(::handleSignUpFailure, ::handleSignUpSuccess)
        }

    fun handleSignUpSuccess(user: FirebaseUser) {
        TODO()
    }

    fun handleSignUpFailure(failure: Failure) {
        TODO()
    }
}