package com.upax.data.data.source.local

import androidx.room.*
import com.upax.data.data.source.local.entity.PokemonEntityDao

@Dao
interface PokedexDao {

    @Query("SELECT * FROM pokemon")
    suspend fun fetchPokemons(): List<PokemonEntityDao>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokemons(pokemon: List<PokemonEntityDao>)

    @Query("DELETE FROM pokemon")
    suspend fun clear()
}