package com.versilistyson.androidstreeteats.presentation.ui.map

import android.widget.Toast
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import java.lang.Exception

sealed class MapHandler(var map: MapboxMap) {

}