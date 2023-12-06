package com.example.mapsdirection

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mapsdirection.databinding.ActivityMainBinding
import com.example.mapsdirection.databinding.NavDrawerLayoutBinding
import com.example.mapsdirection.databinding.ToolbarLayoutBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Create a new instance of the MapsFragment
            val fragment = MapsFragment()

            // Add the fragment to the activity using FragmentManager
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, R.string.open, R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.btnHome -> {
                        val homeFragment = MapsFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment)
                            .commit()
                    }

                    R.id.btnSavedPlaces -> {
                        val savedPlacesFragment = SavedPlacesFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, savedPlacesFragment)
                            .commit()
                    }

                    R.id.btnSetting -> {
                        val settingsFragment = SettingsFragment()
                        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment)
                            .commit()
                    }
                }
                true
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}


