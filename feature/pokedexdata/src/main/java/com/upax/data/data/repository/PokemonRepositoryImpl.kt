package com.upax.data.data.repository

import com.upax.data.data.mapper.PokemonEncounterMapper.encounterEntityToDomain
import com.upax.data.data.mapper.PokemonEvolutionMapper.toDomain
import com.upax.data.data.mapper.PokemonMapper.listToDomain
import com.upax.data.data.mapper.PokemonMapper.listToLocal
import com.upax.data.data.mapper.PokemonMapper.toDomain
import com.upax.data.data.source.local.PokedexDataSourceLocal
import com.upax.data.data.source.remote.PokedexDataSourceRemote
import com.upax.data.domain.model.Encounter
import com.upax.data.domain.model.Pokedex
import com.upax.data.domain.model.Pokemon
import com.upax.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSourceLocal: PokedexDataSourceLocal,
    private val pokemonDataSourceRemote: PokedexDataSourceRemote,
) : PokemonRepository {

    override suspend fun fetchPokemons(): Flow<Pokedex> {
        return pokemonDataSourceRemote.fetchPokemons()
            .onEach {
                pokemonDataSourceLocal.insertPokemons(pokemons = it.listToLocal())
            }.map {
                it.listToDomain()
            }.catch {
                emit(listToDomain(pokemonDataSourceLocal.fetchPokemons()))
            }
    }

    override suspend fun fetchPokemon(pokemonId: String): Flow<Pokemon> {
        return pokemonDataSourceRemote.fetchPokemon(pokemonId).map {
            it.toDomain()
        }
    }

    override suspend fun fetchEncounterPokemon(pokemonId: String): Flow<Encounter> {
        return pokemonDataSourceRemote.fetchEncounterPokemon(pokemonId).map {
            encounterEntityToDomain(it)
        }
    }

    override suspend fun fetchEvolutionPokemon(pokemonId: String): Flow<Boolean> {
        return pokemonDataSourceRemote.fetchSpeciesPokemon(pokemonId).map {
            it.toDomain()
        }
    }
}