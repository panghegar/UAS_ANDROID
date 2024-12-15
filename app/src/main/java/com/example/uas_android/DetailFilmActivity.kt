package com.example.uas_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_android.database.AppDatabase
import com.example.uas_android.database.FilmBookmark
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.activity.viewModels
import com.example.uas_android.databinding.ActivityDetailFilmBinding

class DetailFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFilmBinding
    private val filmViewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari intent
        val judul = intent.getStringExtra("JUDUL") ?: ""
        val director = intent.getStringExtra("DIRECTOR") ?: ""
        val rating = intent.getStringExtra("RATING") ?: ""
        val durasi = intent.getStringExtra("DURASI") ?: ""
        val detail = intent.getStringExtra("DETAIL") ?: ""

        binding.juduluser.text = judul
        binding.directoruser.text = director
        binding.ratinguser.text = rating
        binding.durasiuser.text = durasi
        binding.detailfilm.text = detail

        // Handle tombol favorite
        binding.btnFavorite.setOnClickListener {
            val filmBookmark = FilmBookmark(
                judul = judul,
                director = director,
                rating = rating,
                durasi = durasi,
                detail = detail
            )

            filmViewModel.insertFilm(filmBookmark)
            Toast.makeText(this, "Film ditambahkan ke Bookmark", Toast.LENGTH_SHORT).show()
        }
    }
}

class FilmViewModel(application: Application) : AndroidViewModel(application) {
    private val filmDao = AppDatabase.getDatabase(application).filmDao()

    fun insertFilm(film: FilmBookmark) = viewModelScope.launch {
        filmDao.insertFilm(film)
    }
}