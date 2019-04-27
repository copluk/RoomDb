package com.example.roomdbnote.database.user

import androidx.room.*
import io.reactivex.Single

class SettingDao{

    @Dao
    interface DeviceDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        fun insert(track: SettingEntity.Device)

        @Query("SELECT * FROM Device ORDER BY id DESC")
        fun getAll(): Single<List<SettingEntity.Device>>

        @Query("SELECT * FROM Device WHERE id == :id")
        fun get(id: Int): Single<SettingEntity.Device>

        @Delete
        fun delete(track: SettingEntity.Device): Int
    }
}