package com.example.mobiledevelopmentcourselabapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mobiledevelopmentcourselabapp.data.model.PlayerDbEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PlayerDao {
    @Query("SELECT * FROM ${PlayerDbEntity.TABLE_PLAYERS}")
    fun getAll(): Single<List<PlayerDbEntity>>

    @Insert
    fun insert(playerDbEntity: PlayerDbEntity): Single<Long>

    @Update
    fun update(playerDbEntity: PlayerDbEntity): Completable

    @Delete
    fun delete(playerDbEntity: PlayerDbEntity): Completable
}