package com.upax.ui.detail

import com.upax.data.domain.model.Pokemon

sealed class PokemonDetailUiState {
    data class Success(val pokemon: Pokemon) : PokemonDetailUiState()
    data class Error(val exception: Exception) : PokemonDetailUiState()
    data class LoadingDetail(val isLoad: Boolean) : PokemonDetailUiState()
    data class LoadingEncounter(val isLoad: Boolean) : PokemonDetailUiState()
    data class LoadingEvolution(val isLoad: Boolean) : PokemonDetailUiState()
    data class SuccessEncounter(val string:List<String>) : PokemonDetailUiState()
    data class SuccessEvolution(val isEvolution: Boolean) : PokemonDetailUiState()
}