package com.example.there_help.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.thereapps.Home.pertemuan_10.AspirasiFragment

class BinaDesaTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BeritaFragment()
            1 -> AspirasiFragment()
            2 -> BantuanFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}