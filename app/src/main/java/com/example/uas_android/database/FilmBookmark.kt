package com.example.uas_android.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_bookmark")
data class FilmBookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val director: String,
    val rating: String,
    val durasi: String,
    val detail: String
)

