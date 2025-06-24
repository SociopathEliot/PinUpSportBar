package com.pinup.barapp.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentQrBinding
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pinup.barapp.ui.viewmodels.CartViewModel
import com.pinup.barapp.ui.viewmodels.QrFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class QRFragment : Fragment() {

    private var _binding: FragmentQrBinding? = null
    private val binding get() = _binding!!
    private val qrViewModel: QrFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderId = generateUniqueOrderId()
        val qrBitmap = generateQRCode(orderId)
        binding.ivQr.setImageBitmap(qrBitmap)
        binding.tvOrderId.text = "Order #$orderId"


        binding.btnBackHome.setOnClickListener {
            qrViewModel.clearCart()
            findNavController().popBackStack(R.id.blankFragment, false)
        }
    }


    private fun generateUniqueOrderId(): String {
        val random = (0..9999).random().toString().padStart(4, '0')
        return random
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateQRCode(text: String): Bitmap {
        val size = 512
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
