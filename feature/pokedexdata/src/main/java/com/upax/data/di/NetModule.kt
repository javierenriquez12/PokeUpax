package com.upax.data.di


import com.upax.data.data.repository.PokemonRepositoryImpl
import com.upax.data.data.source.remote.PokedexDataSourceRemote
import com.upax.data.data.source.remote.PokedexDataSourceRemoteImpl
import com.upax.data.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NetModule {

    @Binds
    abstract fun bindsPokemonDataSource(
        pokemonDataSourceRemoteImpl: PokedexDataSourceRemoteImpl
    ): PokedexDataSourceRemote

    @Binds
    abstract fun bindsPokemonRepository(
        pokemonRepository: PokemonRepositoryImpl
    ): PokemonRepository
}