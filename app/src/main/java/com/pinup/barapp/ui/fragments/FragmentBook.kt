package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentBookBinding
import com.pinup.barapp.domain.models.Reservation
import com.pinup.barapp.ui.viewmodels.ReservationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentBook : Fragment(R.layout.fragment_book) {

    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            val reservation = Reservation(
                fullName = binding.etFullName.text.toString(),
                phone = binding.etPhone.text.toString(),
                date = binding.etDate.text.toString(),
                time = binding.etTime.text.toString(),
                tableNumber = binding.etTableNumber.text.toString()
            )
            viewModel.addReservation(reservation)
            findNavController().navigate(R.id.reservationQrFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
