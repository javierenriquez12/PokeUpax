package com.upax.maps

import com.nisuml.mapsdata.domain.model.Location

sealed class MapsState{
    data class Locations(val list: List<Location>) : MapsState()
}
