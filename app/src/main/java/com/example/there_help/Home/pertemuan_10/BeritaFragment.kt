package com.example.there_help.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Data dummy menggunakan objek Berita lokal
        val daftarBeritaLokal = listOf(
            BeritaModel(
                judul = "Pembangunan Fasilitas Air Bersih Desa",
                deskripsi = "Proyek sumur bor sedalam 50 meter telah selesai dibangun untuk warga.",
                tanggal = "01 Juli 2026",
                imageUrl = "https://picsum.photos/200/300?random=10"
            ),
            BeritaModel(
                judul = "Pemberdayaan UMKM Kerajinan Tangan",
                deskripsi = "Ibu-ibu PKK desa sukses mengekspor kerajinan anyaman bambu ke kota.",
                tanggal = "02 Juli 2026",
                imageUrl = "https://picsum.photos/200/300?random=11"
            ),
            BeritaModel(
                judul = "Pelatihan Digitalisasi Administrasi Desa",
                deskripsi = "Aparatur desa kini menggunakan sistem cloud untuk pendataan warga.",
                tanggal = "03 Juli 2026",
                imageUrl = "https://picsum.photos/200/300?random=12"
            )
        )

        // 2. Menggunakan ListViewBeritaAdapter agar tidak bentrok dengan RecyclerView
        val adapter = ListViewBeritaAdapter(requireContext(), daftarBeritaLokal)

        // 3. Pasang ke ListView di fragment_berita.xml
        binding.listViewBerita.adapter = adapter

        // 4. Aksi Klik Item
        binding.listViewBerita.setOnItemClickListener { _, _, position, _ ->
            val terpilih = daftarBeritaLokal[position]
            Toast.makeText(requireContext(), "Membuka: ${terpilih.judul}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}