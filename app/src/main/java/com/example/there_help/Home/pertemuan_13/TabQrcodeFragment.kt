package com.example.there_help.Home.pertemuan_13

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.there_help.R
import com.example.there_help.databinding.FragmentTabQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter


class TabQrcodeFragment : Fragment() {

    private var _binding: FragmentTabQrcodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTabQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGenerate.setOnClickListener {
            val text = binding.edtQrInput.text.toString().trim()
            // Kalau inputan kosong, hentikan proses (biar gak crash)
            if (text.isEmpty()) return@setOnClickListener

            binding.ivQrCode.setImageBitmap(createQR(text))
        }
    }

    // Mesin pencetak QR Code dari ZXing
    private fun createQR(text: String): Bitmap {
        val writer = QRCodeWriter()
        val matrix = writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            500, // Lebar QR Code
            500, // Tinggi QR Code
            mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        )

        // Bikin kanvas kosong RGB_565 (standar warna Android)
        return Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565).apply {
            for (x in 0 until 500) {
                for (y in 0 until 500) {
                    // Cetak pixel hitam kalau matrix bernilai true, sisanya putih
                    setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
