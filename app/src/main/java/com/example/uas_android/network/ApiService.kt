package com.example.uas_android.network

import retrofit2.http.GET
import com.example.uas_android.network.Film
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("film")
    fun getAllFilm(): Call<List<Film>>

    @GET("film/{id}")
    fun getFilmById(@Path("id") filmID : String): Call<List<Film>>

    @POST("film")
    fun postFilm(@Body film: Film) : Call<Film>

    @POST("film/{id}")
    fun updateFilm(@Path("id") filmId: String, @Body film: Film): Call<Film>

    @DELETE("film/{id}")
    fun deleteFilm(@Path("id") filmId: String): Call<Film>
}