package com.example.there_help.Home.pertemuan_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_help.databinding.FragmentBeritaBinding

class BeritaFragment : Fragment() {

    private var _binding: FragmentBeritaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeritaBinding.inflate(inflater, container, false)
        return binding.root
    }

    // List dummy berita perkembangan Bina Desa digital lu
    private val beritaList = listOf(
        BeritaModel("Pembangunan Jembatan Selesai", "Akses jalan penghubung antar RW kini resmi dibuka kembali oleh Kepala Desa.", "25 Mei 2026", "https://picsum.photos/seed/bridge/300/300"),
        BeritaModel("Pelatihan UMKM Desa Digital", "Warga desa antusias mengikuti workshop pemasaran produk kerajinan via e-commerce.", "26 Mei 2026", "https://picsum.photos/seed/shop/300/300"),
        BeritaModel("Jadwal Imunisasi Serentak", "Posyandu Merpati besok pagi mengadakan layanan kesehatan gratis bagi balita.", "27 Mei 2026", "https://picsum.photos/seed/health/300/300"),
        BeritaModel("Kerja Bakti Rutin Bulanan", "Diharapkan kehadirannya untuk seluruh warga dalam rangka membersihkan saluran air utama.", "28 Mei 2026", "https://picsum.photos/seed/clean/300/300"),
        BeritaModel("Pengadaan Sembako Murah", "Balai desa membuka pasar murah khusus warga yang memegang kartu tanda bantuan desa.", "29 Mei 2026", "https://picsum.photos/seed/food/300/300")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BeritaAdapter(beritaList) { selectedItem ->
            Toast.makeText(requireContext(), "Membuka: ${selectedItem.judul}", Toast.LENGTH_SHORT).show()
        }

        binding.rvBerita.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}