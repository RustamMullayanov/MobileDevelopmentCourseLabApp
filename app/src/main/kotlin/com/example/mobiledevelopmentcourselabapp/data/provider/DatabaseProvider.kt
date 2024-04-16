package com.example.mobiledevelopmentcourselabapp.data.provider

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobiledevelopmentcourselabapp.data.database.MIGRATION_1_2
import com.example.mobiledevelopmentcourselabapp.data.database.PlayerDatabase
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import javax.inject.Inject
import javax.inject.Provider

class DatabaseProvider @Inject constructor(
    private val context: Context
) : Provider<RoomDatabase> {
    override fun get(): RoomDatabase {
        return Room.databaseBuilder(
            context,
            PlayerDatabase::class.java,
            PlayerDbEntity.TABLE_PLAYERS
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
}