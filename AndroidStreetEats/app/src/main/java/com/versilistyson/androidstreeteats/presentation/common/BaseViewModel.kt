package com.versilistyson.androidstreeteats.presentation.common

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {



    /*protected open fun handleFailure(failure: Failure, extraStateChanges: ((S) -> S)? = null) {
        when(failure) {
            is Failure.NetworkConnection -> {
                setErrorState(true, failure.e.message, failure, extraStateChanges)
            }
            is Failure.ServerError -> {
                setErrorState(true, failure.e.message, failure, extraStateChanges)
            }
            else -> {
                setErrorState(true, failure.exception.message,failure, extraStateChanges)
            }
        }
    }

    protected open fun setErrorState(
        showError: Boolean,
        errorMessage: String? = null,
        failure: Failure? = null,
        extraStateChanges: ((S) -> S)? = null
    ) {
        onErrorState(showError, errorMessage,failure, extraStateChanges)
    }

    protected open suspend fun setLoadingState(
        isLoading: Boolean,
        extraStateChanges: ((S) -> S)? = null
    ) {
        onLoadingState(isLoading, extraStateChanges)
    }

    private fun onLoadingState(
        isLoading: Boolean,
        extraStateChanges: ((S) -> S)? = null
    ) {
        when(extraStateChanges) {
            null -> {
                setState {
                    this.changeBaseLoadingState(isLoading)
                }
            }
            else -> {
                setState {
                    val newState = extraStateChanges(this)
                    newState.changeBaseLoadingState(isLoading)
                }
            }
        }
    }
    private fun onErrorState(showError: Boolean,
                             errorMessage: String? = null,
                             failure: Failure? = null,
                             extraStateChanges: ((S) -> S)? = null) {
        when(extraStateChanges) {
            null -> {
                setState {
                    this.changeBaseErrorState(showError, errorMessage, failure)
                }
            }
            else -> {
                setState {
                    val newState = extraStateChanges(this)
                    newState.changeBaseErrorState(showError, errorMessage, failure)
                }
            }
        }
    }

    private fun S.changeBaseErrorState(showError: Boolean, errorMessage: String?, failure: Failure?): S {
        val newState = this
        newState.isLoading = false
        newState.showError = showError
        newState.errorMessage = errorMessage
        newState.failure
        return newState
    }
    private fun S.changeBaseLoadingState(isLoading: Boolean): S {
        val newState = this
        newState.isLoading = isLoading
        return newState
    }*/
}