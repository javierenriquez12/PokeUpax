package com.upax.data.data.source.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v2/pokemon")
    suspend fun fetchPokemons(@Query("limit") limit: String): Response<com.upax.data.data.entity.PokedexEntity>

    @GET("api/v2/pokemon/{idPokemon}")
    suspend fun fetchPokemon(@Path("idPokemon") idPokemon: String): Response<com.upax.data.data.entity.pokemon.PokemonEntity>

    @GET("api/v2/pokemon/{idPokemon}/encounters")
    suspend fun fetchEncountersPokemon(@Path("idPokemon") idPokemon: String) : Response<List<com.upax.data.data.entity.pokemon.EncounterEntity>>

    @GET("api/v2/pokemon-species/{idPokemon}")
    suspend fun fetchSpeciesPokemon(@Path("idPokemon") idPokemon: String) : Response<com.upax.data.data.entity.pokemon.SpeciesDetailEntity>
}