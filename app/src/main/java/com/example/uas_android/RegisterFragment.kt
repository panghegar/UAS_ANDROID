package com.example.uas_android

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var radioUser: RadioButton
    private lateinit var radioAdmin: RadioButton
    private lateinit var registerButton: Button
    private lateinit var termsCheckbox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        // Initialize views
        usernameEditText = view.findViewById(R.id.username)
        emailEditText = view.findViewById(R.id.email)
        passwordEditText = view.findViewById(R.id.pass)
        radioUser = view.findViewById(R.id.radio_user)
        radioAdmin = view.findViewById(R.id.radio_admin)
        termsCheckbox = view.findViewById(R.id.checkbox)
        registerButton = view.findViewById(R.id.regisBtn)

        // Handle register button click
        registerButton.setOnClickListener {
            handleRegister(view)
        }

        return view
    }

    private fun handleRegister(view: View) {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val role = if (radioUser.isChecked) "user" else if (radioAdmin.isChecked) "admin" else ""

        // Validate input
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
            TextUtils.isEmpty(password) || TextUtils.isEmpty(role)
        ) {
            Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
            return
        }

        if (!termsCheckbox.isChecked) {
            Snackbar.make(view, "You must agree to the terms and conditions", Snackbar.LENGTH_SHORT).show()
            return
        }

        // Save data to SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("role", role)
        editor.apply()

        val savedEmail = sharedPreferences.getString("email", null)
        val savedPassword = sharedPreferences.getString("password", null)
        val savedRole = sharedPreferences.getString("role", null)

        println("Saved Email: $savedEmail")
        println("Saved Password: $savedPassword")
        println("Saved Role: $savedRole")


        Snackbar.make(view, "Registration successful!", Snackbar.LENGTH_SHORT).show()

        // Optionally navigate to login or other fragment
        // (You can replace this with your navigation logic)
    }
}
