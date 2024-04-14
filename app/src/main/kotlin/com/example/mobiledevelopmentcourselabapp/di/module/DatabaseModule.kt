package com.example.mobiledevelopmentcourselabapp.di.module

import androidx.room.RoomDatabase
import com.example.mobiledevelopmentcourselabapp.data.provider.DatabaseProvider
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideDatabase(databaseProvider: DatabaseProvider): RoomDatabase {
        return databaseProvider.get()
    }
}