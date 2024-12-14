package com.example.uas_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_android.databinding.ActivityHomeAdminBinding
import com.example.uas_android.network.ApiClient
import com.example.uas_android.network.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeAdminBinding
    private lateinit var itemAdapter: FilmAdminAdapter
    private lateinit var itemList: ArrayList<Film>
    private lateinit var recyclerViewItem: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate layout untuk activity admin home
        binding = ActivityHomeAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inisialisasi recyclerview dan adapter untuk daftar film admin
        recyclerViewItem = binding.rvFilm
        recyclerViewItem.setHasFixedSize(true)
        recyclerViewItem.layoutManager = LinearLayoutManager(this)

        itemList = arrayListOf()
        itemAdapter = FilmAdminAdapter(itemList, ApiClient.getInstance())
        recyclerViewItem.adapter = itemAdapter

        // menambahkan listener ke tombol film baru
        binding.btnPlusAdmin.setOnClickListener {
            startActivity(Intent(this@HomeAdminActivity, AdminAddFilmActivity::class.java))
        }

        // menambahkan listener ke tombol kembali
        binding.buttonBack.setOnClickListener {
            // intent ke halaman login
            startActivity(Intent(this@HomeAdminActivity, LoginRegisterActivity::class.java))
            // menutup aktivitas saat ini untuk mencegah kembali ke aktivitas ini dengan tombol kembali
            finish()
        }

        // Mengambil data film dari API
        getFilmList()
    }

    private fun getFilmList() {
        ApiClient.getInstance().getAllFilm().enqueue(object : Callback<List<Film>> {
            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                if (response.isSuccessful) {
                    response.body()?.let { films ->
                        itemList.clear()
                        itemList.addAll(films)
                        itemAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(this@HomeAdminActivity, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Toast.makeText(this@HomeAdminActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
