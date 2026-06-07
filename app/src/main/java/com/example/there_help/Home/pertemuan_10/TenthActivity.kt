    package com.example.there_help.Home.pertemuan_10

    import android.os.Bundle
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.content.ContextCompat
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.there_help.R
    import com.example.there_help.databinding.ActivityTenthBinding
    import com.google.android.material.tabs.TabLayoutMediator

    class TenthActivity : AppCompatActivity() {
        private lateinit var binding: ActivityTenthBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()

            binding = ActivityTenthBinding.inflate(layoutInflater)
            setContentView(binding.root)


            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            val tabsAdapter = BinaDesaTabsAdapter(this)
            // Set adapter ke ViewPager2
            binding.viewPager.adapter = tabsAdapter

            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Berita"
                        tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                        val badge = tab.getOrCreateBadge()
                        badge.isVisible = true
                        badge.number = 20
                    }
                    1 -> {
                        tab.text = "Daftar Aspirasi"
                        tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                        val badge = tab.getOrCreateBadge()
                        badge.isVisible = true
                        badge.number = 5
                    }
                    2 -> {
                        tab.text = "Bantuan"
                        tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)



                    }
                }
            }.attach()

        }
    }