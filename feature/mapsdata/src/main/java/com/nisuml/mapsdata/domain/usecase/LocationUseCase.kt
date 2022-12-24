package com.nisuml.mapsdata.domain.usecase

import com.nisuml.mapsdata.domain.model.Location
import com.nisuml.mapsdata.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(val locationRepository: LocationRepository) : LocationRepository {
    override suspend fun fetchLocations(): Flow<List<Location>> {
        return locationRepository.fetchLocations()
    }

    override fun addLocation(location: Location) {
        return locationRepository.addLocation(location)
    }

}