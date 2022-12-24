package com.nisuml.mapsdata.data.mapper

import android.util.Log
import com.nisuml.mapsdata.domain.model.Location
import com.upax.network.firestore.entity.LocationEntity

object LocationMapper {
    fun LocationEntity.toDomain() = Location(
        latitude = latitude,
        longitude = longitude,
        date = date
    )

    fun Location.toData() = LocationEntity(
        latitude = latitude,
        longitude = longitude,
        date = date
    )

    fun listLocationsToDomain(listLocations: List<LocationEntity>) : List<Location>{
        return listLocations.map {
            Location(it.latitude,it.longitude,it.date)
        }
    }
}