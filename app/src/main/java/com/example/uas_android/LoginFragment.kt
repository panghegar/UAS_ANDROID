package com.example.uas_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

class LoginFragment : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Inisialisasi komponen UI
        emailEditText = view.findViewById(R.id.email)
        passEditText = view.findViewById(R.id.pass)
        loginButton = view.findViewById(R.id.loginBtn)

        loginButton.setOnClickListener {
            handleLogin()
        }

        return view
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString()
        val password = passEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Ambil data dari SharedPreferences
        val sharedPref = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedEmail = sharedPref.getString("email", null)
        val savedPassword = sharedPref.getString("password", null)
        val userRole = sharedPref.getString("role", null)

        // Validasi login
        if (email == savedEmail && password == savedPassword) {
            when (userRole?.lowercase()) { // Normalize ke lowercase
                "user" -> navigateToHomeFragment()
                "admin" -> navigateToAdminActivity()
                else -> Toast.makeText(
                    requireContext(),
                    "Invalid user role",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToHomeFragment() {
//        val homeFragment = HomeFragment()
//        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
//        transaction.replace(R.id.frame_container, homeFragment) // Gunakan ID yang benar dari kontainer
//        transaction.addToBackStack(null)
//        transaction.commit()
        val intent = Intent(requireContext(), BottomNavigationActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }


    private fun navigateToAdminActivity() {
        val intent = Intent(requireContext(), HomeAdminActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
