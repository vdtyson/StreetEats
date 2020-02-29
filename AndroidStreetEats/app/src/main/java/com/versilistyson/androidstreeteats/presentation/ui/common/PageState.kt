package com.versilistyson.androidstreeteats.presentation.ui.common

import com.haroldadmin.vector.VectorState

abstract class PageState(
    var isLoading: Boolean = false,
    var showError: Boolean = false,
    var errorMessage: String = ""
) : VectorState