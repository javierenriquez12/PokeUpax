package com.upax.maps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nisuml.mapsdata.domain.model.Location
import com.upax.maps.databinding.ItemLocationsBinding

class MapsAdapter(val locationList: List<Location>) :
    RecyclerView.Adapter<MapsAdapter.MyViewHolder>() {


    class MyViewHolder(val binding: ItemLocationsBinding) :
        ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemLocationsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(locationList[position]) {
                binding.mapsDateItemTextView.text = "Fecha: $date"
                binding.mapsLocationItemTextView.text = "Ubicación: lat=$latitude, long=$longitude"
            }
        }
    }

    override fun getItemCount() = locationList.size

}