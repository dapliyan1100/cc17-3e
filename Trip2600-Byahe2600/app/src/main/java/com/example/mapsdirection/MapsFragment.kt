package com.example.mapsdirection

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONArray
import org.json.JSONObject

class MapsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var placesClient: PlacesClient
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var floatingLayout: View
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
        mapFragment?.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        // Initialize Places SDK
        Places.initialize(requireContext(), getString(R.string.google_maps_key))
        placesClient = Places.createClient(requireContext())
        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("Places", Context.MODE_PRIVATE)


        val getLocationButton: FloatingActionButton = view.findViewById(R.id.currentLocation)
        val searchButton: FloatingActionButton = view.findViewById(R.id.enableTraffic)
        val searchEditText: TextInputEditText = view.findViewById(R.id.edtPlaceName)
//        val mapbtn: FloatingActionButton = view.findViewById(R.id.btnMapType)


        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            searchPlace(query)
        }

        getLocationButton.setOnClickListener {
            getCurrentLocation()
        }

    }
    override fun onMapReady(map: GoogleMap) {
        // Set custom info window adapter for markers
        googleMap = map
        val baguio = LatLng(16.41639, 120.59306)
        googleMap.addMarker(MarkerOptions().position(baguio).title("Marker in Baguio"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baguio, 15f))
        // Set up click listener for adding markers on map click
        googleMap.setOnMarkerClickListener { marker ->
            val projection = googleMap.projection
            val markerPosition = marker.position
            val markerScreenPosition = projection.toScreenLocation(markerPosition)

            if (!this::floatingLayout.isInitialized) {
                floatingLayout = layoutInflater.inflate(R.layout.info_window_layout, null)
                // Initialize views and button in the floating layout
                val placeNameTextView: TextView = floatingLayout.findViewById(R.id.placeNameTextView)
                val locationTextView: TextView = floatingLayout.findViewById(R.id.locationTextView)
                val saveLocationButton: Button = floatingLayout.findViewById(R.id.saveLocationButton)

                placeNameTextView.text = marker.title
                locationTextView.text = "Location: ${markerPosition.latitude}, ${markerPosition.longitude}"

                saveLocationButton.setOnClickListener {
                    // Perform save location functionality here
                    val latLng = marker.position.toString()
                    savePlaceToLocalStorage(placeNameTextView.text.toString(), latLng)
                }
            }

            // Add the floating layout to the parent view (e.g., a FrameLayout containing the map)
            (view?.parent as ViewGroup).addView(floatingLayout)

            // Position the floating layout at the marker's screen position
            floatingLayout.x = markerScreenPosition.x.toFloat()
            floatingLayout.y = (markerScreenPosition.y - floatingLayout.height).toFloat()

            true // Consume the event to prevent default behavior
        }

        // Handle info window click events
        googleMap.setOnInfoWindowClickListener { marker ->
            // Your logic for handling info window clicks (if needed)
        }
    }
    private fun searchPlace(query: String) {
        googleMap.clear()
        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                if (response.autocompletePredictions.isNotEmpty()) {
                    val prediction = response.autocompletePredictions[0]

                    placesClient.fetchPlace(FetchPlaceRequest.newInstance(prediction.placeId, placeFields))
                        .addOnSuccessListener { fetchPlaceResponse ->
                            val place = fetchPlaceResponse.place
//                            val placeName = place.name ?: "Unnamed Place"

                            place?.let {
                                val latLng = LatLng(place.latLng!!.latitude, place.latLng!!.longitude)
                                addMarker(latLng, place.name!!)
                            }
                        }
                }
            }
    }

    private fun addMarker(latLng: LatLng, title: String) {
        googleMap.addMarker(MarkerOptions().position(latLng).title(title))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
//        val marker = googleMap.addMarker(MarkerOptions().position(latLng).title(title))
//        if (marker != null) {
//            marker.tag = "Any additional marker data you might want to attach"
//        }
    }


    private fun savePlaceToLocalStorage(placeName: String, latLng: String) {
        val placesString = sharedPreferences.getString("places", "[]") ?: "[]"
        val editor = sharedPreferences.edit()

        val jsonArray = JSONArray(placesString)
        val jsonObject = JSONObject()
        jsonObject.put("placeName", placeName)
        jsonObject.put("latLng", latLng)

        jsonArray.put(jsonObject)

        editor.putString("places", jsonArray.toString())
        editor.apply()
        Toast.makeText(requireContext(), "Your place is saved!", Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        googleMap.clear()
                        val currentLatLng = LatLng(it.latitude, it.longitude)
                        googleMap.addMarker(
                            MarkerOptions().position(currentLatLng).title("Current Location")
                        )
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                    } ?: run {
                        Log.e("LocationFailure", "Last known location is null")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("LocationFailure", "Failed to get location: ${e.message}")
                }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }


    companion object {
        private val placeFields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG)
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }
}