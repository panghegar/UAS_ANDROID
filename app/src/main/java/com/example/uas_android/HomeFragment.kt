package com.example.uas_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uas_android.adapter.FilmUserAdapter
import com.example.uas_android.databinding.FragmentHomeBinding
import com.example.uas_android.network.ApiClient
import com.example.uas_android.network.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefManager: PrefManager
    private lateinit var filmAdapter: FilmUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // View Binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize PrefManager
        prefManager = PrefManager.getInstance(requireContext())

        // Tampilkan nama user
        val username = prefManager.getUsername()
        binding.getUsername.text = username

        // Set up RecyclerView
        binding.rvFilm.layoutManager =GridLayoutManager(requireContext(), 2)

        // Ambil data film dari API
        fetchFilmData()
    }

    private fun fetchFilmData() {
        val apiService = ApiClient.getInstance()
        val call = apiService.getAllFilm()

        call.enqueue(object : Callback<List<Film>> {
            override fun onResponse(call: Call<List<Film>>, response: Response<List<Film>>) {
                if (response.isSuccessful) {
                    val filmList = response.body()
                    if (filmList != null) {
                        // Set adapter dengan data film
                        filmAdapter = FilmUserAdapter(filmList)
                        binding.rvFilm.adapter = filmAdapter

                    }
                } else {
                    Toast.makeText(context, "Gagal memuat data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Hindari memory leak
    }
}
