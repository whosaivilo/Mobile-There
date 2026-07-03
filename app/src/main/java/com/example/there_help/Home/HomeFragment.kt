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
import com.example.there_help.Home.pertemuan_3.LoginResultActivity
import com.example.there_help.Home.pertemuan_4.CustomActivity
import com.example.there_help.Home.tugasp2.Kalkulator
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
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar dashboard
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        loadBeritaHome()

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
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

        // Tombol Custom View
        binding.btnKeCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity::class.java)
            intent.putExtra("nama", "Bang Irgi")
            intent.putExtra("asal", "Lab 151")
            intent.putExtra("usia", "30")
            startActivity(intent)
        }

        binding.btnWebView.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Tombol Pusat Bantuan
        binding.btnBantuan.setOnClickListener {
            // Jika HelpActivity ada di folder root/lainnya, pastikan import-nya aman cuq
            // startActivity(Intent(requireContext(), HelpActivity::class.java))
        }

        // Tombol Tab Layout (Materi Pertemuan 10)
        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

        binding.btnTesNotif.setOnClickListener {

            // Bikin kendaraan buat pindah ke halaman Form Pengaduan
            val intentTujuan = Intent(requireContext(), FormPengaduanActivity::class.java)

            startActivity(intentTujuan)

            // 2. Notifikasi tetep jalan
            NotificationHelper.showNotification(
                context = requireContext(),
                title = "Layanan Pengaduan",
                message = "Silakan isi laporan di sini!",
                intent = intentTujuan
            )

            // 3. Alarm tetep jalan
            ReminderHelper.setReminder(
                context = requireContext(),
                hour = 1,
                minute = 30,
                title = "Waktu Pengaduan!",
                message = "Udah 1 menit nih, ayo selesain laporannya!",
                targetActivity = FormPengaduanActivity::class.java
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