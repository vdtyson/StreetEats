package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.data.AppSharedPrefs
import com.versilistyson.androidstreeteats.data.repository.FireAuthRepository
import com.versilistyson.androidstreeteats.domain.entities.UserInfo
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.FetchUserInfo
import com.versilistyson.androidstreeteats.presentation.util.LiveEvent
import javax.inject.Inject

sealed class UserEvent {
    object UserSignedOut : UserEvent() // Create single live events?
    data class FailureGettingUser(private val failure: Failure) : UserEvent()
}

sealed class UserState {
    data class Authenticated(val firebaseUser: FirebaseUser, val userInfo: UserInfo) : UserState()
    object Unauthenticated : UserState()
    // object GuestUser: AppState() -> Make a guest account?
}

class AppViewModel
@Inject constructor(
    fireAuthRepository: FireAuthRepository,
    fetchUserInfo: FetchUserInfo,
    private val appSharedPrefs: AppSharedPrefs
) : ViewModel() {

    val fireAuthInstance = fireAuthRepository.fireAuthInstance
    val phoneAuthProvider = fireAuthRepository.phoneAuthProviderInstance
    private val _userEvent: MutableLiveData<UserEvent> by lazy { MutableLiveData<UserEvent>() }
    val userEvent: LiveEvent<UserEvent> = LiveEvent()


    private val _userState: MutableLiveData<UserState> by lazy { MutableLiveData<UserState>() }
    val userState: LiveData<UserState>
        get() = _userState

    private val authStateListener =
        fireAuthRepository.fireAuthInstance.addAuthStateListener { firebaseAuthResult ->
            fireAuthInstance.addAuthStateListener { fireBaseAuthResult ->
                val firebaseUser = fireBaseAuthResult.currentUser
                if (firebaseUser != null) {
                    fetchUserInfo(
                        viewModelScope,
                        FetchUserInfo.Params(firebaseUser.uid)
                    ) { userInfoResult ->
                        userInfoResult.fold(::handleFailure) { handleSignIn(firebaseUser, it) }
                    }
                } else {
                    _userState.value = UserState.Unauthenticated
                    _userEvent.value = UserEvent.UserSignedOut
                    appSharedPrefs.deleteUserId()
                    appSharedPrefs.deleteAccountSetupCompletionLevel()
                }
            }
        }

    private fun handleSignIn(firebaseUser: FirebaseUser, userInfo: UserInfo) {
        _userState.value = UserState.Authenticated(firebaseUser, userInfo)
        appSharedPrefs.writeUserId(firebaseUser.uid)
        appSharedPrefs.writeAccountSetupCompletionLevel(userInfo.accountSetupCompletionLevel)
    }

    private fun handleFailure(failure: Failure) {
        _userEvent.value = UserEvent.FailureGettingUser(failure)
        appSharedPrefs.deleteUserId()
        appSharedPrefs.deleteAccountSetupCompletionLevel()
    }
}


