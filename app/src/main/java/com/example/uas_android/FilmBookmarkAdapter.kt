package com.example.uas_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_android.database.FilmBookmark
import com.example.uas_android.databinding.ItemFilmBinding

class FilmBookmarkAdapter(private val bookmarkList: List<FilmBookmark>) :
    RecyclerView.Adapter<FilmBookmarkAdapter.BookmarkViewHolder>() {

    inner class BookmarkViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmBookmark) {
            binding.judulFilmUser.text = film.judul
            binding.imageFilmUser.setImageResource(com.example.uas_android.R.drawable.rembulan) // Ganti dengan gambar default
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(bookmarkList[position])
    }

    override fun getItemCount(): Int = bookmarkList.size
}
