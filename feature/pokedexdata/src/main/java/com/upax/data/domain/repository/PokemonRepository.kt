package com.upax.data.domain.repository

import com.upax.data.domain.model.Encounter
import com.upax.data.domain.model.Pokedex
import com.upax.data.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun fetchPokemons() : Flow<Pokedex>
    suspend fun fetchPokemon(pokemonId: String) : Flow<Pokemon>
    suspend fun fetchEncounterPokemon(pokemonId: String) : Flow<Encounter>
    suspend fun fetchEvolutionPokemon(pokemonId: String) : Flow<Boolean>
}