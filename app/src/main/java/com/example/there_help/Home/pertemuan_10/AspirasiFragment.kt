package com.example.thereapps.Home.pertemuan_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.there_help.Home.pertemuan_10.AspirasiModel
import com.example.there_help.databinding.FragmentAspirasiBinding


class AspirasiFragment : Fragment() {

    private var _binding: FragmentAspirasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAspirasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val aspirasiList = listOf(
        AspirasiModel(
            "Ahmad Subarjo",
            "Infrastruktur",
            "Jalanan di gang RT 02 berlubang parah cuq, kalau hujan sering bikin warga kepleset.",
            "25 Mei 2026"
        ),
        AspirasiModel("Siti Aminah", "Layanan", "Pengurusan berkas bansos di balai desa kemarin cepet banget, mantap aslinya!", "26 Mei 2026"),
        AspirasiModel("Budi Budiman", "Infrastruktur", "Mohon dipasang lampu jalan di area makam desa, kalau malam gelap gulita serem bgt.", "27 Mei 2026"),
        AspirasiModel("Dewi Lestari", "Layanan", "Saran jirt, kalau bisa sistem antrean posyandu besok dibuat online biar gak numpuk pagi-pagi.", "28 Mei 2026"),
        AspirasiModel("Eko Prasetyo", "Keamanan", "Pos ronda malam RT 04 butuh pengadaan HT baru biar koordinasi antar warga makin aman.", "29 Mei 2026")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AspirasiAdapter(aspirasiList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih laporan dari ${selectedItem.namaWarga}", Toast.LENGTH_SHORT).show()
        }

        binding.rvAspirasi.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)

            /** Jika ingin model Linear **/
            // layoutManager = LinearLayoutManager(requireContext())

            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}