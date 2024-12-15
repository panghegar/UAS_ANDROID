package com.example.uas_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_android.R
import com.example.uas_android.network.Film

class FilmUserAdapter(private val filmList: List<Film>) :
    RecyclerView.Adapter<FilmUserAdapter.FilmViewHolder>() {

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudulFilm: TextView = itemView.findViewById(R.id.judul_film_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]
        holder.tvJudulFilm.text = film.judulFilm
    }

    override fun getItemCount(): Int = filmList.size
}
