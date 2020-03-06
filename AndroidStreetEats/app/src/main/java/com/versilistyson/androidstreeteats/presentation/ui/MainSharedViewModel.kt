package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.versilistyson.androidstreeteats.data.repository.FireAuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
sealed class AppEvent {
    object NoUserFound: AppEvent() // Create single live events?
}
sealed class AppState {
    data class SignedInUser(val firebaseUser: FirebaseUser) : AppState()
    object NoSignedInUser : AppState()
    // object GuestUser: AppState() -> Make a guest account?
}

class MainSharedViewModel
@Inject constructor(fireAuthRepository: FireAuthRepository) : ViewModel() {

    private val authStateListener =
        fireAuthRepository.fireAuthInstance.addAuthStateListener { firebaseAuthResult ->
            val firebaseUser = firebaseAuthResult.currentUser
            if (firebaseUser != null) {
                _appState.value = AppState.SignedInUser(firebaseUser)
            } else {
                _appState.value = AppState.NoSignedInUser
            }
        }

    private val _appState: MutableLiveData<AppState> by lazy {
        MutableLiveData<AppState>()
    }
    val appState: LiveData<AppState>
        get() = _appState
}


