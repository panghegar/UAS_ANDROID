package com.example.uas_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_android.databinding.ActivityAdminAddFilmBinding
import com.example.uas_android.network.ApiService
import com.example.uas_android.network.Film
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class AdminAddFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminAddFilmBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ppbo-api.vercel.app/IBNZw/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        // Set listener untuk tombol submit
        binding.btnAdd.setOnClickListener {
            addFilmToApi()
        }
    }

    private fun addFilmToApi() {
        // Ambil input dari form
        val title = binding.txtName.text.toString()
        val director = binding.txtDirector.text.toString()
        val duration = binding.txtDurasi.text.toString()
        val rating = binding.txtRating.text.toString()
        val synopsis = binding.txtSinopsis.text.toString()

        // Validasi input
        if (title.isEmpty() || director.isEmpty() || duration.isEmpty() || rating.isEmpty() || synopsis.isEmpty()) {
            Toast.makeText(this, "Harap isi semua kolom!", Toast.LENGTH_SHORT).show()
            return
        }

        // Buat objek Film
        val film = Film(
            judulFilm = title,
            directorFilm = director,
            durasiFilm = duration,
            ratingFilm = rating,
            sinopsisFilm = synopsis
        )

        // Kirim data ke API menggunakan Retrofit
        apiService.postFilm(film).enqueue(object : Callback<Film> {
            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AdminAddFilmActivity, "Film berhasil ditambahkan", Toast.LENGTH_SHORT).show()

                    // Kirim sinyal atau panggil fungsi untuk reload data RecyclerView
                    setResult(RESULT_OK) // Mengirim tanda ke aktivitas sebelumnya
                    finish()
                } else {
                    Toast.makeText(this@AdminAddFilmActivity, "Gagal menambahkan film", Toast.LENGTH_SHORT).show()
                }
            }


            override fun onFailure(call: Call<Film>, t: Throwable) {
                Toast.makeText(this@AdminAddFilmActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
