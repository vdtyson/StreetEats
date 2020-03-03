package com.versilistyson.androidstreeteats.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.haroldadmin.vector.VectorViewModelFactory
import com.haroldadmin.vector.ViewModelOwner
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.squareup.okhttp.Dispatcher
import com.versilistyson.androidstreeteats.domain.common.Either
import com.versilistyson.androidstreeteats.domain.exception.Failure
import com.versilistyson.androidstreeteats.domain.usecase.GetSignedInUser
import com.versilistyson.androidstreeteats.domain.usecase.UseCase.*
import com.versilistyson.androidstreeteats.presentation.ui.common.BaseViewModel
import com.versilistyson.androidstreeteats.presentation.ui.common.PageState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class ActivityState(
    val firebaseUser: FirebaseUser? = null,
    val isSignedIn: Boolean
) : PageState()

class MainSharedViewModel
@AssistedInject constructor(
    @Assisted initialState: ActivityState,
    private val getSignedInUser: GetSignedInUser
) : BaseViewModel<ActivityState>(initialState) {


    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: ActivityState): MainSharedViewModel
    }

    fun getSignedInFirebaseUser() = viewModelScope.launch {
        setLoadingState(true)
        launch(Dispatchers.IO) {
            getSignedInUser(this, NoParams()) { result ->
                result.fold(::handleUserFetchFailure,::handleUserFetchSuccess)
            }
        }
    }

    private fun handleUserFetchSuccess(firebaseUser: FirebaseUser?) {
        if(firebaseUser != null) {
            setState {
                copy(firebaseUser = firebaseUser, isSignedIn = true)
            }
        } else {
            setErrorState(true, "User not found.") {
                setState {
                    this.resetSignedInUser()
                }
            }
        }
    }

    private fun handleUserFetchFailure(failure: Failure) {
        setErrorState(true, "User not logged in.") {
            setState {
                this.resetSignedInUser()
            }
        }
    }



}

private fun ActivityState.resetSignedInUser() =
    copy(firebaseUser = null,isSignedIn = false)
