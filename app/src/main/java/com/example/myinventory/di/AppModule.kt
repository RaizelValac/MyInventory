package com.example.myinventory.di

import android.app.Application
import androidx.room.Room
import com.example.myinventory.data.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): InventoryDatabase {
        return Room.databaseBuilder(app,
            InventoryDatabase::class.java,
            "inventory_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(db: InventoryDatabase) = db.getDao()
}

