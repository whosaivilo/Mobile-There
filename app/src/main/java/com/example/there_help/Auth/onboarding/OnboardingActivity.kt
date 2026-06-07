package com.example.there_help.Auth.onboarding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.there_help.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Kumpulkan 3 fragment tutorial onboarding
        val fragmentsList = listOf(
            Onboarding1Fragment(),
            Onboarding2Fragment(),
            Onboarding3Fragment()
        )

        // Inisialisasi adapter dan pasang ke ViewPager2
        val adapter = OnboardingAdapter(this, fragmentsList)
        binding.viewPagerOnboarding.adapter = adapter

        // Gandengkan indicator bulat-bulat bawaan library lu
        binding.dotIndicator.attachTo(binding.viewPagerOnboarding)
    }
}