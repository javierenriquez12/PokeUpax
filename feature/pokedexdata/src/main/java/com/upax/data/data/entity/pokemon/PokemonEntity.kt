package com.upax.data.data.entity.pokemon

data class PokemonEntity(
    val abilities: List<com.upax.data.data.entity.pokemon.AbilitiesEntity>,
    val types: List<com.upax.data.data.entity.pokemon.TypesEntity>,
    val moves: List<com.upax.data.data.entity.pokemon.MovesEntity>,
    val location_area_encounters: String,
    val species: com.upax.data.data.entity.pokemon.SpeciesEntity,
    val name: String
)
