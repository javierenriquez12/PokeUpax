package com.nisuml.mapsdata.data.source.remote

import com.nisuml.mapsdata.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationDataSourceRemote {
    suspend fun fetchLocations() : Flow<List<Location>>

    fun addLocation(location: Location)
}