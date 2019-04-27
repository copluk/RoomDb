package com.example.roomdbnote.database.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SettingEntity.User::class, SettingEntity.Device::class],
    version = 1
)
@TypeConverters(SettingEntity.DataConverter::class)
abstract class SettingRoomDb: RoomDatabase() {

    abstract fun deviceDao(): SettingDao.DeviceDao

    companion object {
        var INSTANCE: SettingRoomDb? = null

        fun getRoomDb(context: Context?): SettingRoomDb? {
            if (INSTANCE == null) {
                synchronized(SettingRoomDb::class) {

                    INSTANCE = Room.databaseBuilder(
                        context?.applicationContext!!,
                        SettingRoomDb::class.java,
                        "setting"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}