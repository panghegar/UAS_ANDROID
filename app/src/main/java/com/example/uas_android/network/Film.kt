package com.example.uas_android.network

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("_id")
    val `id`: String,

    @SerializedName("judulFilm")
    val `judulFilm`: String,

    @SerializedName("directorFilm")
    val `directorFilm`: String,

    @SerializedName("durasiFilm")
    val `durasiFilm`: String,

    @SerializedName("ratingFilm")
    val `ratingFilm`: String,

    @SerializedName("sinopsisFilm")
    val `sinopsisFilm`: String
)
