package com.versilistyson.androidstreeteats.presentation.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.versilistyson.androidstreeteats.presentation.common.UIEvent
import com.versilistyson.androidstreeteats.presentation.common.UIState

sealed class MapState() : UIState() {

    data class Customer(
        val vendorMarkers: List<Nothing> = TODO()
    ) : MapState()

    data class Vendor(
        val isTracking: List<Nothing> = TODO()
    ) : MapState()
}

class MapViewModel : ViewModel() {
    private val _mapState: MutableLiveData<MapState> by lazy {
        MutableLiveData<MapState>()
    }
    val mapState: LiveData<MapState>
        get() = _mapState

    val _uiEvent: MutableLiveData<UIEvent> by lazy {
        MutableLiveData<UIEvent>()
    }

    val uiEvent: LiveData<UIEvent>
        get() = _uiEvent
}