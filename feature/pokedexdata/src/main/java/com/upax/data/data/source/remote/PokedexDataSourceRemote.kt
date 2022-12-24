package com.upax.data.data.source.remote

import kotlinx.coroutines.flow.Flow

interface PokedexDataSourceRemote {
    suspend fun fetchPokemons(): Flow<com.upax.data.data.entity.PokedexEntity>
    suspend fun fetchPokemon(pokemonId: String): Flow<com.upax.data.data.entity.pokemon.PokemonEntity>
    suspend fun fetchEncounterPokemon(pokemonId: String) : Flow<List<com.upax.data.data.entity.pokemon.EncounterEntity>>
    suspend fun fetchSpeciesPokemon(pokemonId: String) : Flow<com.upax.data.data.entity.pokemon.SpeciesDetailEntity>
}