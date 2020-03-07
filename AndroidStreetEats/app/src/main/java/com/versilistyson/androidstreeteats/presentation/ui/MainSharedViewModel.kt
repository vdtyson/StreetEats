package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.versilistyson.androidstreeteats.data.repository.FireAuthRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

sealed class UserEvent {
    object UserSignedOut : UserEvent() // Create single live events?
}

sealed class UserState {
    data class SignedInUser(val firebaseUser: FirebaseUser) : UserState()
    object NoSignedInUser : UserState()
    // object GuestUser: AppState() -> Make a guest account?
}

class MainSharedViewModel
@Inject constructor(fireAuthRepository: FireAuthRepository) : ViewModel() {

    val fireAuthInstance = fireAuthRepository.fireAuthInstance
    val phoneAuthProvider = fireAuthRepository.phoneAuthProviderInstance
    private val _userEvent: MutableLiveData<UserEvent> by lazy { MutableLiveData<UserEvent>() }
    val userEvent: LiveData<UserEvent>
        get() = _userEvent

    private val _userState: MutableLiveData<UserState> by lazy { MutableLiveData<UserState>() }
    val userState: LiveData<UserState>
        get() = _userState

    private val authStateListener =
        fireAuthRepository.fireAuthInstance.addAuthStateListener { firebaseAuthResult ->
            val firebaseUser = firebaseAuthResult.currentUser
            when (userState.value) {
                is UserState.SignedInUser -> {
                    if (firebaseUser == null) {
                        _userEvent.value = UserEvent.UserSignedOut
                        _userState.value = UserState.NoSignedInUser
                    }
                }
                UserState.NoSignedInUser -> {
                    if (firebaseUser != null) {
                        _userState.value = UserState.SignedInUser(firebaseUser)
                    }
                }
                null -> {
                    _userState.value = UserState.NoSignedInUser
                }
            }
        }
}


