package com.upax.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nisuml.mapsdata.domain.model.Location
import com.upax.maps.databinding.FragmentMapsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment(), OnMapReadyCallback {
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var locationManager: LocationManager? = null
    private var myLocation = LatLng(-11.111111, -77.111111) //Lima, Perú
    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
    private var listLocation = listOf<Location>()
    val PERMISSION_ALL = 1
    private val mapsViewModel: MapsViewModel by viewModels()
    private lateinit var binding: FragmentMapsBinding
    private fun getMyLocation() {

        if (locationManager == null) locationManager =
            requireActivity().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val task = fusedLocationProviderClient?.lastLocation
                task?.addOnSuccessListener {
                    myLocation = LatLng(it.latitude, it.longitude)
                }
                task?.addOnFailureListener {
                    Toast.makeText(
                        activity,
                        "Hubo un problema al cargar el mapa",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationManager =
            requireActivity().getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        getMyLocation()
        activity?.requestPermissions(permissions, PERMISSION_ALL)
        mapsViewModel.fetchLocations()
        mapsViewModel.mapState.observe(viewLifecycleOwner) {
            when (it) {
                is MapsState.Locations -> {
                    this.listLocation = it.list
                    setUpListLocationsInfo(it.list)
                    val mapFragment =
                        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
                    mapFragment?.getMapAsync(this)
                }
            }
        }
    }

    private fun setUpListLocationsInfo(list: List<Location>) {
        binding.mapsLocationsRecyclerView.apply {
            adapter = MapsAdapter(list)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15.0f))
        listLocation.forEach {
            googleMap.addMarker(
                MarkerOptions().position(
                    LatLng(
                        it.latitude.toDouble(),
                        it.longitude.toDouble()
                    )
                )
            )
        }
    }
}