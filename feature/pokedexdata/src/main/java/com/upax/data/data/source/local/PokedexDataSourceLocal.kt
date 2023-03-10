package com.upax.data.data.source.local

import com.upax.local.PokedexDao
import com.upax.local.entity.PokemonEntityDao
import javax.inject.Inject

class PokedexDataSourceLocal @Inject constructor(private val dao: PokedexDao) {

    suspend fun fetchPokemons() : List<PokemonEntityDao> = dao.fetchPokemons()

    suspend fun insertPokemons(pokemons: List<PokemonEntityDao>){
        dao.insertPokemons(pokemons)
    }

    suspend fun clear() {
        dao.clear()
    }
}