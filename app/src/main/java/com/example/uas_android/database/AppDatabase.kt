package com.example.uas_android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmBookmark::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "film_bookmark_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
