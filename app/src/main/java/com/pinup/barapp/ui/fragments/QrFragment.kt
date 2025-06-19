package com.pinup.barapp.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentQrBinding
import com.pinup.barapp.ui.MainActivity
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.navigation.fragment.findNavController

class QRFragment : Fragment() {

    private var _binding: FragmentQrBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<QRFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        // Генерируем QR-код с orderId или уникальным текстом
        val orderId = args.orderId
        val qrBitmap = generateQRCode(orderId)
        binding.ivQr.setImageBitmap(qrBitmap)
        binding.tvOrderId.text = "Order #$orderId"
        // Здесь можно также добавить Estimated wait и кнопку

        binding.btnBackHome.setOnClickListener {
        // Или navController.navigate
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
        _binding = null
    }

    private fun generateQRCode(text: String): Bitmap {
        val size = 512 // размер QR
        val bits = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size)
        val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bmp[x, y] =
                    if (bits[x, y]) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            }
        }
        return bmp
    }
}
