package com.example.uas_android.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_table")
data class Film(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int = 0,
    val judulFilm: String,
    val directorFilm: String,
    val durasiFilm: String,
    val ratingFilm: String,
    val sinopsisFilm: String,
    val imgFilm: String
)
