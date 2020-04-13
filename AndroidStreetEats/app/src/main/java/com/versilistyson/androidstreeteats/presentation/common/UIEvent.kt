package com.versilistyson.androidstreeteats.presentation.common

import com.versilistyson.androidstreeteats.domain.exception.Failure

open class UIEvent {
    object Loading: UIEvent()
    object Success: UIEvent()
    data class Fail(val message: String? = null, val failure: Failure, val state: UIState? = null): UIEvent()
    data class BadOrWrongState(val currentState: UIState? = null): UIEvent()
}