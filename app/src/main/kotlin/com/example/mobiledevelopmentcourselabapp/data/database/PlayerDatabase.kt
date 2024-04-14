package com.example.mobiledevelopmentcourselabapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobiledevelopmentcourselabapp.data.converters.PlayerPositionConverter
import com.example.mobiledevelopmentcourselabapp.data.dao.PlayerDao
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity

@Database(entities = [PlayerDbEntity::class], version = 1)
@TypeConverters(PlayerPositionConverter::class)
abstract class PlayerDatabase: RoomDatabase() {
    abstract fun notificationsDao(): PlayerDao
}