package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorState
import com.versilistyson.androidstreeteats.domain.exception.Failure

abstract class PageState(
    var isLoading: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: String? = null,
    var failure: Failure? = null
) : VectorState