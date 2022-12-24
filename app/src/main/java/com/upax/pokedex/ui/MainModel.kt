package com.upax.pokedex.ui

sealed class MainModel{
    data class RequestTimerOnFinished(val isFinished: Boolean) : MainModel()
}
