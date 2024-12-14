package com.example.uas_android.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmDao {
    @Insert
    suspend fun insert(film: Film)

    @Update
    suspend fun update(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query("SELECT * FROM film_table ORDER BY id ASC")
    suspend fun getAllFilm(): List<Film>

    @Query("SELECT * FROM film_table WHERE id = :filmId LIMIT 1")
    suspend fun getFilmById(filmId: Int): Film?
}