package com.nisuml.mapsdata.domain.repository

import com.nisuml.mapsdata.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun fetchLocations() : Flow<List<Location>>

    fun addLocation(location: Location)
}