package com.versilistyson.androidstreeteats.presentation.ui.map

import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style

import com.versilistyson.androidstreeteats.R
import com.versilistyson.androidstreeteats.databinding.FragmentMapBinding
import com.versilistyson.androidstreeteats.di.activityInjector
import com.versilistyson.androidstreeteats.di.util.DaggerViewModelFactory
import com.versilistyson.androidstreeteats.presentation.ui.AppViewModel
import com.versilistyson.androidstreeteats.presentation.ui.UserState
import com.versilistyson.androidstreeteats.presentation.common.fragment.BaseFragment
import com.versilistyson.androidstreeteats.presentation.util.showToastMessage
import java.lang.Exception
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MapFragment : BaseFragment() {

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    @Inject
    lateinit var locationEngine: LocationEngine

    @Inject
    lateinit var locationEngineRequest: LocationEngineRequest

    private lateinit var map: MapboxMap

    private val permissionsManager: PermissionsManager by lazy {
        PermissionsManager(permissionsListener)
    }


    private val appViewModel: AppViewModel by activityViewModels {
        daggerViewModelFactory
    }

    private lateinit var mapBinding: FragmentMapBinding

    private val currentLocation: MutableLiveData<Location> by lazy {
        MutableLiveData<Location>()
    }

    private val previousLocation: MutableLiveData<Location> by lazy {
        MutableLiveData<Location>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityInjector.inject(this)
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), this.getString(R.string.mapboxToken))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapBinding = FragmentMapBinding.inflate(inflater, container, false)
        mapBinding.lifecycleOwner = this
        mapBinding.mapView.onCreate(savedInstanceState)
        mapBinding.mapView.getMapAsync(onMapReadyCallback)
        return mapBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentLocation.observe(this.viewLifecycleOwner, Observer {onLocationChanged(it)})
        appViewModel.userState
            .observe(this.viewLifecycleOwner,
                Observer { userState ->
                    when (userState) {
                        is UserState.Authenticated -> {
                            showWelcomeMessage(userState.userInfo.email)
                        }
                        UserState.Unauthenticated -> {
                            TODO()
                        }
                    }
                }
            )
    }

    override fun onResume() {
        super.onResume()
        mapBinding.mapView.onResume()
    }

    @SuppressWarnings("MissingPermission")
    override fun onStart() {
        super.onStart()
        mapBinding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapBinding.mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapBinding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapBinding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapBinding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapBinding.mapView.onSaveInstanceState(outState)
    }

    private fun showWelcomeMessage(email: String) {
        showToastMessage("Hello $email!")
    }

    private fun onLocationChanged(currentLocation: Location) {
        val previousLocation = previousLocation.value
        val currentLatLng = LatLng(currentLocation.latitude, currentLocation.longitude)
        if(previousLocation != null) {
            if(currentLatLng != LatLng(previousLocation.latitude, previousLocation.longitude)) {
                setCameraPosition(currentLocation)
                showToastMessage("Lat: ${currentLocation.latitude} Lon: ${currentLocation.longitude}")
            }
        }
    }

    private fun setCameraPosition(location: Location) {
        map.animateCamera(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    location.latitude,
                    location.longitude
                )
            )
        )
    }

    private fun enableLocation() {
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            initializeLocationComponent()
            initializeLocationEngine()

        } else {
            permissionsManager.requestLocationPermissions(requireActivity())
        }
    }

    @SuppressWarnings("MissingPermission")
    private fun initializeLocationComponent() {
        map.locationComponent.activateLocationComponent(
            LocationComponentActivationOptions.builder(
                requireContext(),
                map.style!!
            ).build()
        )
        map.locationComponent.isLocationComponentEnabled = true
        map.locationComponent.cameraMode = CameraMode.TRACKING
    }

    @SuppressWarnings("MissingPermission")
    private fun initializeLocationEngine() {
        locationEngine.requestLocationUpdates(
            locationEngineRequest,
            locationEngineCallback,
            requireActivity().mainLooper
        )
    }

    private val permissionsListener = object : PermissionsListener {
        override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
            showToastMessage(
                "This app needs location permission to be able to show your location on the map",
                Toast.LENGTH_LONG
            )
        }

        override fun onPermissionResult(granted: Boolean) {
            if (granted) {
                enableLocation()
            } else {
                showToastMessage("User Location was not granted", Toast.LENGTH_LONG)
                requireActivity().finish()
            }
        }

    }

    private val onMapReadyCallback = OnMapReadyCallback { mapboxMap ->
        map = mapboxMap
        map.setStyle(Style.MAPBOX_STREETS) {
            enableLocation()
        }
    }

    private val locationEngineCallback = object : LocationEngineCallback<LocationEngineResult> {

        override fun onSuccess(result: LocationEngineResult?) {
            val prevLocation = currentLocation.value
            val lastLocation = result?.lastLocation ?: return
            previousLocation.value = prevLocation
            currentLocation.value = lastLocation
        }

        override fun onFailure(exception: Exception) {
            showToastMessage(exception.message ?: "", Toast.LENGTH_LONG)
        }
    }

}
