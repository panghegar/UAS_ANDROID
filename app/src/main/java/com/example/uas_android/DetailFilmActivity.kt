package com.example.uas_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailFilmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)

        // Ambil data dari Intent
        val judul = intent.getStringExtra("JUDUL")
        val director = intent.getStringExtra("DIRECTOR")
        val rating = intent.getStringExtra("RATING")
        val durasi = intent.getStringExtra("DURASI")
        val detail = intent.getStringExtra("DETAIL")

        // Set data ke tampilan
        findViewById<TextView>(R.id.juduluser).text = judul
        findViewById<TextView>(R.id.directoruser).text = director
        findViewById<TextView>(R.id.ratinguser).text = rating
        findViewById<TextView>(R.id.durasiuser).text = durasi
        findViewById<TextView>(R.id.detailfilm).text = detail

        // Tombol Back
        findViewById<ImageView>(R.id.back).setOnClickListener {
            onBackPressed()
        }
    }
}
