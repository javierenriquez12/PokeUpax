package com.upax.data.di

import android.content.Context
import androidx.room.Room
import com.upax.data.data.source.local.PokedexDB
import com.upax.data.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PokedexDB::class.java,
        Constants.database_name
    ).build()

    @Provides
    fun provideNewsDao(db: PokedexDB) = db.pokedexDao()
}