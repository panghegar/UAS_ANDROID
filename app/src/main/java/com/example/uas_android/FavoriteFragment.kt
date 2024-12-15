package com.example.uas_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uas_android.adapter.FilmBookmarkAdapter
import com.example.uas_android.database.AppDatabase
import com.example.uas_android.database.FilmBookmark
import com.example.uas_android.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FilmBookmarkAdapter
    private var bookmarkList = mutableListOf<FilmBookmark>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadBookmarks()
    }

    private fun setupRecyclerView() {
        adapter = FilmBookmarkAdapter(bookmarkList)
        binding.rvFilm.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvFilm.adapter = adapter
    }

    private fun loadBookmarks() {
        val database = AppDatabase.getDatabase(requireContext())
        lifecycleScope.launch {
            try {
                bookmarkList.clear()
                bookmarkList.addAll(database.filmDao().getAllBookmarks())
                adapter.notifyDataSetChanged()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
