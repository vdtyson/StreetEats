package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.GetFirebaseUser
import com.versilistyson.androidstreeteats.domain.usecase.UseCase
import javax.inject.Inject

class SharedViewModel
@Inject constructor(val getFirebaseUser: GetFirebaseUser): ViewModel() {
    private val _firebaseUser: MutableLiveData<FirebaseUser?> by lazy {
        MutableLiveData<FirebaseUser?>()
    }
    val firebaseUser: LiveData<FirebaseUser?>
    get() = _firebaseUser

    fun getFirebaseUser() {
        getFirebaseUser.invoke(viewModelScope, UseCase.NoParams()) { result ->
            result.fold(::handleGetUserFailure,::handleGetUserSuccess)
        }
    }

    private fun handleGetUserSuccess(firebaseUser: FirebaseUser?) {
        _firebaseUser.value = firebaseUser
    }
    private fun handleGetUserFailure(failure: Failure) {
        TODO("Handle Failure")
    }
}