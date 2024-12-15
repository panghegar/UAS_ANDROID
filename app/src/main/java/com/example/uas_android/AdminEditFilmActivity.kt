package com.example.uas_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uas_android.databinding.ActivityAdminEditFilmBinding
import com.example.uas_android.network.ApiService
import com.example.uas_android.network.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdminEditFilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminEditFilmBinding
    private lateinit var apiService: ApiService
    private var filmId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminEditFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://ppbo-api.vercel.app/IBNZw/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        // Ambil data dari Intent
        filmId = intent.getStringExtra("id")
        binding.txtTitleEdit.setText(intent.getStringExtra("judulFilm"))
        binding.txtDirectorEdit.setText(intent.getStringExtra("directorFilm"))
        binding.txtDurasiEdit.setText(intent.getStringExtra("durasiFilm"))
        binding.txtRatingEdit.setText(intent.getStringExtra("ratingFilm"))
        binding.txtSinopsisEdit.setText(intent.getStringExtra("sinopsisFilm"))

        // Tombol kembali
        binding.buttonBack.setOnClickListener {
            finish()
        }

        // Tombol update
        binding.btnUpdate.setOnClickListener {
            updateFilm()
        }
    }

    private fun updateFilm() {
        val updatedFilm = Film(
            id = filmId,
            judulFilm = binding.txtTitleEdit.text.toString(),
            directorFilm = binding.txtDirectorEdit.text.toString(),
            durasiFilm = binding.txtDurasiEdit.text.toString(),
            ratingFilm = binding.txtRatingEdit.text.toString(),
            sinopsisFilm = binding.txtSinopsisEdit.text.toString()
        )

        if (filmId != null) {
            apiService.updateFilm(filmId!!, updatedFilm).enqueue(object : Callback<Film> {
                override fun onResponse(call: Call<Film>, response: Response<Film>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AdminEditFilmActivity, "Film berhasil diupdate!", Toast.LENGTH_SHORT).show()

                        // Kirim data film yang diperbarui
                        val intent = Intent()
                        intent.putExtra("updatedFilmId", filmId)
                        intent.putExtra("updatedTitle", binding.txtTitleEdit.text.toString())
                        intent.putExtra("updatedDirector", binding.txtDirectorEdit.text.toString())
                        intent.putExtra("updatedDurasi", binding.txtDurasiEdit.text.toString())
                        intent.putExtra("updatedRating", binding.txtRatingEdit.text.toString())
                        intent.putExtra("updatedSinopsis", binding.txtSinopsisEdit.text.toString())
                        setResult(RESULT_OK, intent)

                        finish()
                    } else {
                        Toast.makeText(this@AdminEditFilmActivity, "Gagal mengupdate film", Toast.LENGTH_SHORT).show()
                    }
                }


                override fun onFailure(call: Call<Film>, t: Throwable) {
                    Toast.makeText(this@AdminEditFilmActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "ID Film tidak ditemukan!", Toast.LENGTH_SHORT).show()
        }
    }
}
