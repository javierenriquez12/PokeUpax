package com.upax.data.data.mapper

import com.upax.data.data.source.local.entity.PokemonEntityDao
import com.upax.data.domain.model.Pokedex
import com.upax.data.domain.model.Pokemon
import com.upax.data.domain.model.Result

object PokemonMapper {
    fun com.upax.data.data.entity.PokedexEntity.listToDomain() = Pokedex(
        count = this.count,
        next = this.next,
        previous = this.previous,
        pokemonList = this.results.mapIndexed { index, it ->
            Result(
                (index + 1).toString(),
                it.name,
                it.url
            )
        }
    )

    fun com.upax.data.data.entity.pokemon.PokemonEntity.toDomain() = Pokemon(
        abilities = this.abilities.map { it.ability.name },
        types = this.types.map { it.type.name },
        moves = this.moves.map { it.move.name },
        speciesUrl = this.species.url,
        locationEncounter = this.location_area_encounters,
        name = this.name
    )

    fun com.upax.data.data.entity.PokedexEntity.listToLocal() =
        this.results.mapIndexed { index, it ->
            PokemonEntityDao(
                index.toString(),
                it.name,
                it.url
            )
        }

    fun listToDomain(pokemonList: List<PokemonEntityDao>) =
        Pokedex(
            pokemonList = pokemonList.mapIndexed { index, it ->
                Result(
                    (index + 1).toString(),
                    it.name,
                    it.url
                )
            }
        )
}