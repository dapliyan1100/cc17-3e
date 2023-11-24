package com.example.trip2600_byahe2600

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class DashboardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        if (savedInstanceState == null) {
            // Create a new instance of the MapsFragment
            val fragment = MapsFragment()

            // Add the fragment to the activity using FragmentManager
            supportFragmentManager.beginTransaction()
                .replace(R.id.map_container, fragment)
                .commit()
        }

    }

}
