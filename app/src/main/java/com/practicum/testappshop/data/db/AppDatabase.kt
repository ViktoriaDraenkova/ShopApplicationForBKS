package com.practicum.testappshop.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practicum.testappshop.data.db.dao.PackDao
import com.practicum.testappshop.data.db.entity.BarcodeEntity
import com.practicum.testappshop.data.db.entity.PackEntity
import com.practicum.testappshop.data.db.entity.PackPriceEntity
import com.practicum.testappshop.data.db.entity.UnitEntity

@Database(
    entities = [BarcodeEntity::class, PackEntity::class, PackPriceEntity::class, UnitEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun packDao(): PackDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "product_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}