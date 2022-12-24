package com.nisuml.mapsdata.data.repository

import com.nisuml.mapsdata.data.source.remote.LocationDataSourceRemote
import com.nisuml.mapsdata.domain.model.Location
import com.nisuml.mapsdata.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationDataSourceRemote: LocationDataSourceRemote
) : LocationRepository {
    override suspend fun fetchLocations(): Flow<List<Location>> {
        return locationDataSourceRemote.fetchLocations()
    }

    override fun addLocation(location: Location) {
        locationDataSourceRemote.addLocation(location)
    }
}