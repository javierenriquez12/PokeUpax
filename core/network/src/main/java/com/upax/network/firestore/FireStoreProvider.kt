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
        firestore.collection(Constants.LOCATIONS_COLLECTION)
            .add(locationEntity)
    }

    suspend fun getLocation(): List<LocationEntity> {
        return firestore.collection(Constants.LOCATIONS_COLLECTION).get().await().documents.map {
            it.toObject(LocationEntity::class.java) as LocationEntity
        }
    }
}