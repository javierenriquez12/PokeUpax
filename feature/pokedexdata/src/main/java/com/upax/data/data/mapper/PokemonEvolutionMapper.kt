package com.upax.data.data.mapper

object PokemonEvolutionMapper {

    fun com.upax.data.data.entity.pokemon.SpeciesDetailEntity.toDomain() = !this.forms_switchable
}