package com.example.uas_android.database

import androidx.room.*

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(film: FilmBookmark)

    @Query("SELECT * FROM film_bookmark")
    suspend fun getAllBookmarks(): List<FilmBookmark>

    @Delete
    suspend fun deleteFilm(film: FilmBookmark)
}
