package com.example.uas_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uas_android.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    // mengikat tata letak aktivitas menggunakan view binding
    private lateinit var binding: ActivitySplashScreenBinding
    // menentukan lamanya splashscreen yaitu 3000milidetik atau 3 detik
    private val SPLASH_DISPLAY_LENGTH = 2000
    // metode yang dipanggil ketika activity dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mengatur tingkat transparansi awal elemen dengan ID ivLogo menjadi 0 (transparan)
        binding.ivLogo.alpha = 0f
        // menerapkan animasi secara singat, logo akan memudar dari transparan (0) menjadi tidak transparan(1) selama 3 detik
        binding.ivLogo.animate().setDuration(2000).alpha(1f).withEndAction() {
            // intent untuk berpindah ke activity welcomeactivity
            val mainIntent = Intent(this@SplashScreenActivity, WelcomeActivity::class.java)
            // memulai activity welcomeactivity
            startActivity(mainIntent)
            // menutup activity spashscreen, shg pengguna tidak dapat kembali ke halaman ini dgn menekan tombol kembali
            finish()
        }
    }
}