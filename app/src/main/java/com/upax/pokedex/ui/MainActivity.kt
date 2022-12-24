package com.upax.pokedex.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.upax.pokedex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var locationManager: LocationManager? = null
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    val PERMISSION_ALL = 1

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        requestPermissions(permissions, PERMISSION_ALL)
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        mainViewModel.stateModel.observe(this) {
            when (it) {
                is MainModel.RequestTimerOnFinished -> {
                    if (it.isFinished) {
                        requestLocation()
                        mainViewModel.start()
                    }
                }
            }
        }
    }

    private fun requestLocation() {
        if (locationManager == null) locationManager =
            getSystemService(LOCATION_SERVICE) as LocationManager
        if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val task = fusedLocationProviderClient.lastLocation
                task.addOnSuccessListener {
                    mainViewModel.saveLocations(
                        it.latitude.toString(),
                        it.longitude.toString()
                    )
                }
            }
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            requestLocation()
            mainViewModel.start()
        }
    }
}
