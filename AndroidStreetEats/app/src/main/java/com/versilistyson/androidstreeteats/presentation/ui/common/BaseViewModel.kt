package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorViewModel

abstract class BaseViewModel<S : PageState>(initialState: S) : VectorViewModel<S>(initialState) {
    protected open fun setLoadingState(fn: (() -> Any)? = null) {
        when(fn) {
            null ->
                setPageLoadingState()
            else -> {
                fn.invoke()
                setPageLoadingState()
            }
        }
    }

    protected open fun setNonLoadingState(fn: (() -> Any)? = null) {
        when(fn) {
            null ->
                setPageLoadingState()
            else -> {
                fn.invoke()
                setPageNonLoadingState()
            }
        }
    }



    protected open fun setErrorState(message: String, fn: (() -> Any)? = null) =
        when (fn) {
            null ->
                setPageErrorState(message)
            else -> {
                fn.invoke()
                setPageErrorState(message)
            }
        }

    protected  open fun setNonErrorState(fn: (() -> Any)? = null) =
        when(fn) {
            null ->
              setPageNonErrorState()
            else -> {
                fn.invoke()
                setPageNonErrorState()
            }
        }

    private fun setPageNonErrorState() {
        setState {
            this._showError = false
            this._errorMessage = ""
            this
        }
    }
    private fun setPageErrorState(message: String) {
        setState {
            this._showError = true
            this._errorMessage = message
            this
        }
    }

    private fun setPageLoadingState() {
        setState {
            this._isLoading = true
            this
        }
    }
    private fun setPageNonLoadingState() {
        setState {
            this._isLoading = false
            this
        }
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
