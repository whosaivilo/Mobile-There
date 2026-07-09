package com.example.there_help.Home.pertemuan_13

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.there_help.R
import com.example.there_help.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Hidupin Tombol Back di Toolbar
        binding.toolbarThirteenth.setNavigationOnClickListener {
            finish()
        }

        val adapter = ThirteenthTabsAdapter(this)
        binding.viewPagerThirteenth.adapter = adapter

        TabLayoutMediator(binding.tabLayoutThirteenth,
            binding.viewPagerThirteenth) { tab, position ->
            tab.text = when (position) {
                0 -> "Capture"
                1 -> "Scan"
                2 -> "QR Code"
                else -> ""
            }
        }.attach()
    }
}