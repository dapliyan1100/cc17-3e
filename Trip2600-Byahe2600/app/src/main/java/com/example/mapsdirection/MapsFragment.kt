package com.example.mapsdirection

import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MapsFragment : Fragment(){
    private var mGoogleMap: GoogleMap? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var permissionToRequest = mutableListOf<String>()
    private var isLocationPermissionOk = false
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var currentLocation: Location
    private var currentMarker: Marker? = null
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val baguio = LatLng(16.41639, 120.59306)
        googleMap.addMarker(MarkerOptions().position(baguio).title("Marker in Baguio"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(baguio))
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.homeMap) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

//        val currentLocation = view.findViewById<FloatingActionButton>(R.id.currentLocation)
//        currentLocation.setOnClickListener {
//            getCurrentLocation()
//        }
    }



//    private fun getCurrentLocation() {
//        val fusedLocationProviderClient =
//            LocationServices.getFusedLocationProviderClient(requireContext())
//
//        if (ActivityCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
////            isLocationPermissionOk = false
//            return
//        }
//        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
//
//            currentLocation = it
//            moveCameraToLocation(currentLocation)
//        }.addOnFailureListener {
//            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun moveCameraToLocation(location: Location) {
//
//        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
//            LatLng(
//                location.latitude,
//                location.longitude
//            ), 17f
//        )
//
//        val markerOption = MarkerOptions()
//            .position(LatLng(location.latitude, location.longitude))
//            .title("Current Location")
//            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
//
//        currentMarker?.remove()
//        currentMarker = mGoogleMap?.addMarker(markerOption)
//        currentMarker?.tag = 703
//        mGoogleMap?.animateCamera(cameraUpdate)
//
//    }

}