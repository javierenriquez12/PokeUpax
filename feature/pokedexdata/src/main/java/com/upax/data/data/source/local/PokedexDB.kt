package com.upax.data.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.upax.data.data.source.local.entity.PokemonEntityDao

@Database(entities = [PokemonEntityDao::class], version = 1, exportSchema = false)
abstract class PokedexDB : RoomDatabase() {
    abstract fun pokedexDao() : PokedexDao
}