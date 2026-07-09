package com.example.there_help.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.there_help.Auth.AuthActivity
import com.example.there_help.FormPengaduanActivity
import com.example.there_help.Home.pertemuan_10.BeritaAdapter
import com.example.there_help.Home.pertemuan_10.TenthActivity
import com.example.there_help.Home.pertemuan_13.ThirteenthActivity
import com.example.there_help.Home.pertemuan_3.LoginResultActivity
import com.example.there_help.Home.pertemuan_4.CustomActivity
import com.example.there_help.Home.tugasp2.Kalkulator
import com.example.there_help.RingkasanPengaduanActivity
import com.example.there_help.WebViewActivity
import com.example.there_help.data.api.BeritaApiClient
import com.example.there_help.databinding.FragmentHomeBinding
import com.example.there_help.utils.NotificationHelper
import com.example.there_help.utils.ReminderHelper
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        loadBeritaHome()

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)


        binding.btnLogout.setOnClickListener { view ->
            com.google.android.material.dialog.MaterialAlertDialogBuilder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // Nampilin Snackbar
                    com.google.android.material.snackbar.Snackbar.make(view,
                        "Anda berhasil logout!",
                        com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).show()

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()

                    // Menghapus Activity dari stack
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    com.google.android.material.snackbar.Snackbar.make(
                        requireView(), // Nempel ke layar biar gak ketutupan
                        "Logout dibatalkan!",
                        com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
                    ).show() // <- Ini buat nampilin Snackbar

                    dialog.dismiss()
                }
                .show()
        }

        // Tombol Kalkulator
        binding.btnKeKalkulator.setOnClickListener {
            startActivity(Intent(requireContext(), Kalkulator::class.java))
        }

        // Tombol Hasil Skor
        binding.btnKeResult.setOnClickListener {
            startActivity(Intent(requireContext(), LoginResultActivity::class.java))
        }

        // Tombol Custom View (Revisi putExtra)
        binding.btnKeCustom2.setOnClickListener {
            // 1. Menampilkan Toast
            Toast.makeText(requireContext(), "Beralih ke halaman materi...", Toast.LENGTH_SHORT).show()

            // 2. Menggantinya dengan navigasi ke Activity baru
            val intent = Intent(requireContext(), CustomActivity::class.java)
            intent.putExtra("title", "5 Cara Mengelola Stok")
            intent.putExtra("desc", "Optimasi inventori agar tidak rugi.")
            startActivity(intent)
        }


        // Tombol Web View
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        binding.btnCamera.setOnClickListener {
            startActivity(Intent(requireContext(), ThirteenthActivity::class.java))
        }
        // Tombol Tab Layout (Materi Pertemuan 10)
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        // Tombol Tes Notifikasi & Pindah Halaman
        binding.btnTesNotif.setOnClickListener {
            // 1. Pindah Halaman
            val intentTujuan = Intent(requireContext(), RingkasanPengaduanActivity::class.java)
            startActivity(intentTujuan)


            // 3. Alarm tetep jalan (Warning: ini diset 1 jam 30 menit ya!)
            ReminderHelper.setReminder(
                context = requireContext(),
                hour = 1,
                minute = 30,
                title = "Waktu Pengaduan!",
                message = "Udah masuk waktunya nih, ayo selesain laporannya!",
                targetActivity = FormPengaduanActivity::class.java,
                nama = "",
                kategori = "",
                isiLaporan = ""
            )
        }
    }

    private fun loadBeritaHome() {
        lifecycleScope.launch {
            try {
                val response = BeritaApiClient.apiService.getBerita()
                val beritaList = response.articles ?: emptyList()

                // Inisialisasi adapter berita dengan data dari server
                val adapter = BeritaAdapter(beritaList)

                binding.rvBeritaHome.adapter = adapter
                binding.rvBeritaHome.layoutManager = LinearLayoutManager(requireContext())

            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Gagal memuat berita: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}