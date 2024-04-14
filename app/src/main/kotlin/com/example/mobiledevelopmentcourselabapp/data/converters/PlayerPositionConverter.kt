package com.example.mobiledevelopmentcourselabapp.data.converters

import androidx.room.TypeConverter
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition

class PlayerPositionConverter {
    @TypeConverter
    fun toPlayerPosition(positionString: String?): PlayerPosition? {
        return positionString?.let { PlayerPosition.getByName(it) }
    }

    @TypeConverter
    fun fromPlayerPosition(position: PlayerPosition?): String? {
        return position?.name
    }
}