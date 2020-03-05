package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User
import com.haroldadmin.vector.withState
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.exception.feature_failure.FireAuthFailure
import com.versilistyson.androidstreeteats.domain.usecase.FetchUserInfo
import com.versilistyson.androidstreeteats.domain.usecase.FetchFirebaseUser
import com.versilistyson.androidstreeteats.domain.usecase.UseCase.*
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.PageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainSharedViewModel
@Inject constructor(
    private val fetchFirebaseUser: FetchFirebaseUser,
    private val fetchUserInfo: FetchUserInfo
) : ViewModel() {

    private val _firebaseUser: MutableLiveData<FirebaseUser> by lazy {
        MutableLiveData<FirebaseUser>()
    }
    val firebaseUser: LiveData<FirebaseUser>
        get() = _firebaseUser

    private val _userInfo: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }

    val userInfo: LiveData<UserInfo>
        get() = _userInfo

    private val _failure: MutableLiveData<Failure> by lazy {
        MutableLiveData<Failure>()
    }

    val failure: LiveData<Failure>
        get() = _failure

    private val _errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val isLoading: LiveData<Boolean>
        get() = _isLoading


    fun getFirebaseUser() = viewModelScope.launch{
        _isLoading.value = true
        fetchFirebaseUser(this, NoParams()) { firebaseUserResult ->
            firebaseUserResult.fold(
                ::handleFailure,
                ::handleFirebaseUserFetchSuccess
            )
        }
    }

    fun getUserInfo() {
        val firebaseUser = _firebaseUser
        if (firebaseUser.value != null) {
            fetchUserInfo(viewModelScope, FetchUserInfo.Params(firebaseUser.value!!.uid))
        } else {
            handleFailure(FireAuthFailure.NoFirebaseUser)
        }
    }

    private fun handleFirebaseUserFetchSuccess(firebaseUser: FirebaseUser?) {
        _isLoading.value = false
       _firebaseUser.postValue(firebaseUser)
    }

    private fun handleUserInfoFetchSuccess(userInfo: UserInfo) {
        _userInfo.postValue(userInfo)
    }


    private fun handleFailure(
        failure: Failure
    ) {
        _failure.postValue(failure)
        _errorMessage.postValue(failure.exception.message)
        /*when (failure) {
            is FireAuthFailure.CredentialsExpired ->
                _errorMessage.postValue("Resign Credentials")
            is FireAuthFailure.NoFirebaseUser ->
                _errorMessage.postValue("User not found")
            is FireAuthFailure.Other ->
                _errorMessage.postValue("Fireauth failure")
        }*/
    }

}


