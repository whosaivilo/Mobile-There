package com.example.there_help.Auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.there_help.BaseActivity
import com.example.there_help.R
import com.example.there_help.Auth.onboarding.OnboardingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        lifecycleScope.launch {
            delay(2000) // Delay splash screen 2 detik


            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)


            val targetActivity = if (isLogin) {
                BaseActivity::class.java
            } else {
                OnboardingActivity::class.java
            }

            // Eksekusi perpindahan halaman
            val intent = Intent(this@SplashScreenActivity, targetActivity)
            startActivity(intent)
            finish()
        }
    }
}