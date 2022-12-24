package com.nisuml.mapsdata.di

import com.nisuml.mapsdata.data.repository.LocationRepositoryImpl
import com.nisuml.mapsdata.data.source.remote.LocationDataSourceRemote
import com.nisuml.mapsdata.data.source.remote.LocationDataSourceRemoteImpl
import com.nisuml.mapsdata.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsPokemonDataSource(
        locationDataSourceRemoteImpl: LocationDataSourceRemoteImpl
    ): LocationDataSourceRemote

    @Binds
    abstract fun bindsPokemonRepository(
        locationRepository: LocationRepositoryImpl
    ): LocationRepository
}