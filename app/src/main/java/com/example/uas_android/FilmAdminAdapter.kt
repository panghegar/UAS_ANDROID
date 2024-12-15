package com.example.uas_android

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uas_android.HomeAdminActivity.Companion.REQUEST_CODE_EDIT_FILM
import com.example.uas_android.databinding.ItemAdminFilmBinding
import com.example.uas_android.network.ApiService
import com.example.uas_android.network.Film
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmAdminAdapter(
    private val listFilm: ArrayList<Film>,
    private val client: ApiService,
) :
    RecyclerView.Adapter<FilmAdminAdapter.ItemFilmViewHolder>() {
    inner class ItemFilmViewHolder(private val binding: ItemAdminFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Film) {
            with(binding) {
                titleFilmAdmin.text = data.judulFilm
                directorFilmAdmin.text = data.directorFilm
                durasiFilmAdmin.text = data.durasiFilm
                ratingFilmAdmin.text = data.ratingFilm
                sinopsisFilmAdmin.text = data.sinopsisFilm

                btnEdit.setOnClickListener {
                    val intentToEdit = Intent(itemView.context, AdminEditFilmActivity::class.java)
                    intentToEdit.putExtra("id", data.id)
                    intentToEdit.putExtra("judulFilm", data.judulFilm)
                    intentToEdit.putExtra("directorFilm", data.directorFilm)
                    intentToEdit.putExtra("durasiFilm", data.durasiFilm)
                    intentToEdit.putExtra("ratingFilm", data.ratingFilm)
                    intentToEdit.putExtra("sinopsisFilm", data.sinopsisFilm)
                    (itemView.context as Activity).startActivityForResult(intentToEdit, REQUEST_CODE_EDIT_FILM)
                }

                btnHapus.setOnClickListener {
                    val response = data.id?.let { it1 -> client.deleteFilm(it1) }

                    if (response != null) {
                        response.enqueue(object : Callback<Film> {
                            override fun onResponse(call: Call<Film>, response: Response<Film>) {
                                if (response.isSuccessful) {
                                    Toast.makeText(
                                        itemView.context,
                                        "Film ${data.judulFilm} berhasil dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val position = adapterPosition

                                    if (position != RecyclerView.NO_POSITION) {
                                        removeItem(position)
                                    }
                                } else {
                                    Log.e("API Error", "Response not successful or body is null")
                                }
                            }

                            override fun onFailure(call: Call<Film>, t: Throwable) {
                                Toast.makeText(itemView.context, "Koneksi error", Toast.LENGTH_LONG)
                                    .show()
                            }
                        })
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFilmViewHolder {
        // Inflate layout item_admin_film.xml menggunakan binding
        val binding = ItemAdminFilmBinding.inflate(
            android.view.LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemFilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemFilmViewHolder, position: Int) {
        // Bind data ke tampilan pada posisi tertentu
        val film = listFilm[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        // Return jumlah item dalam list
        return listFilm.size
    }

    fun updateData(newFilmList: List<Film>) {
        val listFilm = newFilmList
        notifyDataSetChanged() // Memberitahu RecyclerView untuk memperbarui tampilan
    }

    fun removeItem(position: Int) {
        listFilm.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listFilm.size)
    }

    fun updateItem(position: Int, newFilm: Film) {
        listFilm[position] = newFilm
        notifyItemChanged(position)
    }

}