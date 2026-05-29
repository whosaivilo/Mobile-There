package com.example.there_help.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.there_help.AuthActivity
import com.example.there_help.Home.pertemuan_10.TenthActivity
import com.example.there_help.WebViewActivity
import com.example.there_help.databinding.FragmentHomeBinding
import com.example.there_help.Home.pertemuan_3.LoginResultActivity
import com.example.there_help.Home.pertemuan_4.CustomActivity
import com.example.there_help.Home.tugasp2.Kalkulator

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // Tombol ke WebView
        binding.btnWebView.setOnClickListener {
            val intent = Intent(requireContext(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol ke Kalkulator
        binding.btnKeKalkulator.setOnClickListener {
            val intent = Intent(requireContext(), Kalkulator::class.java)
            startActivity(intent)
        }

        // Tombol ke Result
        binding.btnKeResult.setOnClickListener {
            val intent = Intent(requireContext(), LoginResultActivity::class.java)
            startActivity(intent)
        }

        // Tombol ke Custom View
        binding.btnKeCustom2.setOnClickListener {
            val intent = Intent(requireContext(), CustomActivity::class.java)
            startActivity(intent)
        }
        binding.btnBantuan.setOnClickListener {
            val intent = Intent(requireContext(), HelpActivity::class.java)
            startActivity(intent)
        }
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }


        // Tombol Logout (Pakai requireActivity().finish() sesuai modul hal 14)
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish() // Destroy BaseActivity
                }
                .setNegativeButton("Batal") { dialog, _ -> dialog.dismiss() }
                .show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}