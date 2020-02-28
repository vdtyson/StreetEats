package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorState

abstract class PageState(
    var _isLoading: Boolean = false,
    var _showError: Boolean = false,
    var _errorMessage: String = ""
) : VectorState