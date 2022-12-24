package com.upax.network.firestore.entity

@kotlinx.serialization.Serializable
data class LocationEntity(
    val latitude: String,
    val longitude: String,
    val date: String
)
