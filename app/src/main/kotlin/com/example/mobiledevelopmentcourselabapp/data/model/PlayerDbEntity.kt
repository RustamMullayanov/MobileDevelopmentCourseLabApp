package com.example.mobiledevelopmentcourselabapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition

@Entity(tableName = PlayerDbEntity.TABLE_PLAYERS)
class PlayerDbEntity(
    @PrimaryKey val uid: Long? = null,
    val name: String,
    val number: Int,
    val position: PlayerPosition
){
    companion object {
        const val TABLE_PLAYERS = "players"
    }
}


