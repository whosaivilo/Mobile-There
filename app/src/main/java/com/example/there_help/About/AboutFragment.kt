package com.example.there_help.About

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.there_help.R
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.there_help.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Pasang Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "About Bina Desa"
        }

        // 2. Siapkan data array untuk ListView
        val daftarFitur = arrayOf(
            "Dashboard: Pantauan Total Pengaduan",
            "Statistik & Grafik: Visualisasi data",
            "5 Pengaduan Terbaru",
            "Daftar Pengaduan",
            "Data Kategori",
            "Penilaian Layanan",
            "Tindak Lanjut",
            "Data Warga & User"
        )

        // 3. Inisialisasi ArrayAdapter menggunakan layout bawaan sistem
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_list_putih, // <-- Ubah bagian ini
            daftarFitur
        )


        // 4. Pasang adapter ke ListView
        binding.listViewFitur.adapter = adapter

        // 5. Tangani kejadian klik pada item ListView
        binding.listViewFitur.setOnItemClickListener { _, _, position, _ ->
            val fiturTerpilih = daftarFitur[position]
            Toast.makeText(requireContext(), "Info: $fiturTerpilih", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}