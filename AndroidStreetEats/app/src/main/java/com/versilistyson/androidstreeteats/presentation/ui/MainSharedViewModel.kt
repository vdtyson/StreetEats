package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.versilistyson.androidstreeteats.data.repository.FireAuthRepository
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.FetchUserInfo
import com.versilistyson.androidstreeteats.presentation.util.LiveEvent
import java.util.concurrent.TimeUnit
import javax.inject.Inject

sealed class UserEvent {
    object UserSignedOut : UserEvent() // Create single live events?
    data class FailureGettingUser(private val failure: Failure) : UserEvent()
}

sealed class UserState {
    data class SignedInUser(val firebaseUser: FirebaseUser, val userInfo: UserInfo) : UserState()
    object NoSignedInUser : UserState()
    // object GuestUser: AppState() -> Make a guest account?
}

class MainSharedViewModel
@Inject constructor(fireAuthRepository: FireAuthRepository, fetchUserInfo: FetchUserInfo) :
    ViewModel() {

    val fireAuthInstance = fireAuthRepository.fireAuthInstance
    val phoneAuthProvider = fireAuthRepository.phoneAuthProviderInstance
    private val _userEvent: MutableLiveData<UserEvent> by lazy { MutableLiveData<UserEvent>() }
    val userEvent: LiveEvent<UserEvent> = LiveEvent()


    private val _userState: MutableLiveData<UserState> by lazy { MutableLiveData<UserState>() }
    val userState: LiveData<UserState>
        get() = _userState

    private val authStateListener =
        fireAuthRepository.fireAuthInstance.addAuthStateListener { firebaseAuthResult ->
            val firebaseUser = firebaseAuthResult.currentUser
            when (userState.value) {
                is UserState.SignedInUser -> {
                    val currentUserId = (userState.value as UserState.SignedInUser).firebaseUser.uid
                    if (firebaseUser == null) {
                        _userEvent.value = UserEvent.UserSignedOut
                        _userState.value = UserState.NoSignedInUser
                    } else if (firebaseUser.uid != currentUserId) {
                        fetchUserInfo(viewModelScope, FetchUserInfo.Params(firebaseUser.uid)) {
                            it.fold(::handleFailure) { result ->
                                handleSignIn(
                                    firebaseUser,
                                    result
                                )
                            }
                        }
                    }
                }
                UserState.NoSignedInUser -> {
                    if (firebaseUser != null) {
                        fetchUserInfo(viewModelScope, FetchUserInfo.Params(firebaseUser.uid)) {
                            it.fold(::handleFailure) { result ->
                                handleSignIn(
                                    firebaseUser,
                                    result
                                )
                            }
                        }
                    }
                }
                null -> {
                    _userState.value = UserState.NoSignedInUser
                }
            }
        }

    private fun handleSignIn(firebaseUser: FirebaseUser, userInfo: UserInfo) {
        _userState.value = UserState.SignedInUser(firebaseUser, userInfo)
    }

    private fun handleFailure(failure: Failure) {
        _userEvent.value = UserEvent.FailureGettingUser(failure)
    }
}


