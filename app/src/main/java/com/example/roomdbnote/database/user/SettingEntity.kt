package com.example.roomdbnote.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*



class SettingEntity {

    @Entity(tableName = "User")
    data class User(
        var name: String? = "",
        var account: String? = "",
        @ColumnInfo(name = "nick_name")
        var nickName: String? = "",
        var hasGame: Boolean? = false,
        var gamePrice: Double? = 0.0
    ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
    }

    @Entity(tableName = "Device")
    data class Device(
        var deviceName: String = "",
        var type: Int? = 0,
        val userId: Int,
        val ownDate: Date
    ) {
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
    }


    class DataConverter {
        @TypeConverter
        fun toDate(value: Long?): Date? {
            return if (value == null) null else Date(value)
        }

        @TypeConverter
        fun toLong(value: Date?): Long? {
            return value?.time
        }
    }
}