package com.upax.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.*
import com.upax.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homePokedexCardView.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.upax.pokedex.app/pokedexFragment".toUri())
                .build()
            Navigation.findNavController(view).navigate(request)
        }
        binding.homeProfileCardView.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.upax.pokedex.app/profileFragment".toUri())
                .build()
            Navigation.findNavController(view).navigate(request)
        }
        binding.homeMapsCardView.setOnClickListener {
            val request = NavDeepLinkRequest.Builder
                .fromUri("android-app://com.upax.pokedex.app/mapsFragment".toUri())
                .build()
            Navigation.findNavController(view).navigate(request)
        }
    }
}