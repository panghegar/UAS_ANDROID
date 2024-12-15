package com.example.uas_android.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_android.DetailFilmActivity
import com.example.uas_android.R
import com.example.uas_android.network.Film
import com.example.uas_android.databinding.ItemFilmBinding

class FilmUserAdapter(private val filmList: List<Film>) :
    RecyclerView.Adapter<FilmUserAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.judulFilmUser.text = film.judulFilm
            binding.imageFilmUser.setImageResource(R.drawable.rembulan) // Ganti sesuai dengan data
            // Tambahkan OnClickListener
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailFilmActivity::class.java).apply {
                    putExtra("JUDUL", film.judulFilm)
                    putExtra("DIRECTOR", film.directorFilm)
                    putExtra("RATING", film.ratingFilm)
                    putExtra("DURASI", film.durasiFilm)
                    putExtra("DETAIL", film.sinopsisFilm)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(filmList[position])
    }

    override fun getItemCount(): Int = filmList.size
}
