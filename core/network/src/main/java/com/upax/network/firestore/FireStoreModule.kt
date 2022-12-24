package com.upax.network.firestore

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FireStoreModule {
    @Singleton
    @Provides
    fun provideFireStore(): FireStoreProvider {
        return FireStoreProvider(FirebaseFirestore.getInstance())
    }
}