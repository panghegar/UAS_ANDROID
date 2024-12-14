package com.example.uas_android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var binding: BottomNavigationActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_navigation_activity)
//        binding = BottomNavigationActivity.inf

        // menginisialisasi elemen UI seperti BottomNavigationView
        bottomNavigation = findViewById(R.id.bottom_navigation)

        // menangani item yang dipilih pada BottomNavigationView
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    // memilih fragment HomeFragment ketika item Home dipilih
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.nav_favorite -> {
                    // memilih fragment FavoriteFragment ketika item Favorite dipilih
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.nav_profile -> {
                    // memilih fragment ProfileFragment ketika item Profile dipilih
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
        // menetapkan fragment default sebagai HomeFragment
        replaceFragment(HomeFragment())
    }

    // fungsi untuk menggantikan fragment yang ditampilkan di dalam container
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}