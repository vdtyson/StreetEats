package com.versilistyson.androidstreeteats.presentation.common

import com.versilistyson.androidstreeteats.domain.exception.Failure

open class UIState {
    object Empty: UIState()
    object Loading : UIState()
    object Success : UIState()
    data class Failed(
        val message: String? = null,
        val failure: Failure? = null,
        val previouStae: UIState? = null
    ) : UIState()
}