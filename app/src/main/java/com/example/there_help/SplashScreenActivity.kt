package com.example.there_help

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        val intent = Intent(this, AuthActivity::class.java)

        lifecycleScope.launch {
            delay(2000) //simulasi pengambilan data selama 2 detik`

//            cek login
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            val targetActivity = if (isLogin) {
                MainActivity::class.java
            } else {
                AuthActivity::class.java
            }


            startActivity(intent)
            finish()

        }
    }
}