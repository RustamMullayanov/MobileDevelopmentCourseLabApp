package com.example.mobiledevelopmentcourselabapp.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobiledevelopmentcourselabapp.domain.model.PlayerPosition

@Entity(tableName = PlayerDbEntity.TABLE_PLAYERS)
class PlayerDbEntity(
    @PrimaryKey val uid: Long? = null,
    val name: String,
    val number: Int,
    val position: PlayerPosition,
    val avatarUri: Uri?
){
    companion object {
        const val TABLE_PLAYERS = "players"
    }
}


