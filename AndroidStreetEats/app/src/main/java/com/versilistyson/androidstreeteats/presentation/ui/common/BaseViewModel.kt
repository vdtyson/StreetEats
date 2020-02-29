package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorViewModel

abstract class BaseViewModel<S : PageState>(initialState: S) : VectorViewModel<S>(initialState) {


    protected open fun setErrorState(
        showError: Boolean,
        errorMessage: String = "",
        extraStateActions: (() -> Unit)? = null
    ) {
        when(extraStateActions) {
            null -> {
                setState {
                    this.changeBaseErrorState(showError, errorMessage)
                }
            }
            else -> {
                extraStateActions()
                setState {
                    this.changeBaseErrorState(showError, errorMessage)
                }
            }
        }
    }

    protected open suspend fun setLoadingState(
        isLoading: Boolean,
        extraStateActions: (() -> Unit)? = null
    ) {
        when(extraStateActions) {
            null -> {
                setState {
                    this.changeBaseLoadingState(isLoading)
                }
            }
            else -> {
                extraStateActions()
                setState {
                    this.changeBaseLoadingState(isLoading)
                }
            }
        }
    }

    private fun S.changeBaseErrorState(showError: Boolean, errorMessage: String): S {
        val newState = this
        newState.isLoading = false
        newState.showError = showError
        newState.errorMessage = errorMessage
        return newState
    }
    private fun S.changeBaseLoadingState(isLoading: Boolean): S {
        val newState = this
        newState.isLoading = isLoading
        return newState
    }
}
/*
abstract class BaseViewModel(initialPageState: PageState)<S: PageState> : VectorViewModel<PageState>(initialPageState) {
    protected open fun setLoadingState() {
        setState { copy(_isLoading = true)  }
    }
    protected open fun setErrorState(errorMessage: String) {
        setState { copy(_errorMessage = errorMessage, _showError = true) }
    }
}*/
