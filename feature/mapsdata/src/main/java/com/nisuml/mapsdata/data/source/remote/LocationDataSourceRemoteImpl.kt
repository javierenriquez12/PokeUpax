package com.nisuml.mapsdata.data.source.remote

import com.nisuml.mapsdata.data.mapper.LocationMapper
import com.nisuml.mapsdata.data.mapper.LocationMapper.toData
import com.nisuml.mapsdata.domain.model.Location
import com.upax.network.firestore.FireStoreProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationDataSourceRemoteImpl @Inject constructor(
    private val fireStore: FireStoreProvider
) : LocationDataSourceRemote {
    override suspend fun fetchLocations(): Flow<List<Location>> {
        return flow {
            emit(LocationMapper.listLocationsToDomain(fireStore.getLocation()))
        }.flowOn(Dispatchers.IO)
    }

    override fun addLocation(location: Location) {
        fireStore.addLocation(location.toData())
    }
}