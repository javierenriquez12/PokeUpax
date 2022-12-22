package com.upax.data.data.mapper

import com.upax.data.domain.model.Encounter

object PokemonEncounterMapper {

    fun encounterEntityToDomain(listEncounter: List<com.upax.data.data.entity.pokemon.EncounterEntity>): Encounter {
        return Encounter(nameEncounters = listEncounter.map {
            it.location_area.name
        })
    }
}