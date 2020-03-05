package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorState
import com.versilistyson.androidstreeteats.domain.exception.Failure

abstract class PageState<S: PageState<S>>(
    var isLoading: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: String? = null,
    var failure: Failure? = null
) : VectorState {
    abstract fun resetToDefaultState():  S
    protected fun S.changeBaseErrorState(showError: Boolean, errorMessage: String?, failure: Failure?): S {
        val newState = this
        newState.isLoading = false
        newState.showError = showError
        newState.errorMessage = errorMessage
        newState.failure
        return newState
    }
    protected fun S.changeBaseLoadingState(isLoading: Boolean): S {
        val newState = this
        newState.isLoading = isLoading
        return newState
    }
}