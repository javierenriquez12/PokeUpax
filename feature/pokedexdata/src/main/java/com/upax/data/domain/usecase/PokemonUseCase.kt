package com.upax.data.domain.usecase

import com.upax.data.domain.model.Encounter
import com.upax.data.domain.model.Pokedex
import com.upax.data.domain.model.Pokemon
import com.upax.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) :
    PokemonRepository {
    override suspend fun fetchPokemons(): Flow<Pokedex> {
        return pokemonRepository.fetchPokemons()
    }

    override suspend fun fetchPokemon(pokemonId: String): Flow<Pokemon> {
        return pokemonRepository.fetchPokemon(pokemonId)
    }

    override suspend fun fetchEncounterPokemon(pokemonId: String): Flow<Encounter> {
        return pokemonRepository.fetchEncounterPokemon(pokemonId)
    }

    override suspend fun fetchEvolutionPokemon(pokemonId: String): Flow<Boolean> {
        return pokemonRepository.fetchEvolutionPokemon(pokemonId)
    }
}