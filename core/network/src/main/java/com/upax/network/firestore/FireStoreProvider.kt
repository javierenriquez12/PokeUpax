package com.upax.network.firestore

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.upax.network.firestore.entity.LocationEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreProvider @Inject constructor(private val firestore: FirebaseFirestore) {
    fun addLocation(locationEntity: LocationEntity) {
        firestore.collection("locations")
            .add(locationEntity)
    }

    suspend fun getLocation(): List<LocationEntity> {
        return firestore.collection("locations").get().await().documents.map {
            Log.d("prueba","122")
            val local = it.toObject(LocationEntity::class.java)
            LocationEntity("1231", "13231", "31231")
        }
    }
}