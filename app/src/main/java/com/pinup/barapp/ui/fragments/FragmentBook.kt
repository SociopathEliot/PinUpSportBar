package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
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
        setupPhoneMask()
        setupTablePrefix()
        setupDatePicker()
        setupTimePicker()
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

    private fun setupPhoneMask() {
        val mask = "+7(###)###-###"
        binding.etPhone.addTextChangedListener(object : TextWatcher {
            private var editing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (editing) return
                editing = true
                val digits = s.toString().filter { it.isDigit() }.take(9)
                val builder = StringBuilder()
                var index = 0
                for (ch in mask) {
                    if (ch == '#') {
                        if (index < digits.length) builder.append(digits[index++])
                    } else {
                        builder.append(ch)
                    }
                }
                binding.etPhone.setText(builder.toString())
                binding.etPhone.setSelection(binding.etPhone.text.length)
                editing = false
            }
        })
    }

    private fun setupTablePrefix() {
        val prefix = "Table Number - #"
        binding.etTableNumber.setText(prefix)
        binding.etTableNumber.setSelection(binding.etTableNumber.text.length)
        binding.etTableNumber.addTextChangedListener(object : TextWatcher {
            private var editing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (editing) return
                editing = true
                val digits = s.toString().removePrefix(prefix).filter { it.isDigit() }
                val result = prefix + digits
                binding.etTableNumber.setText(result)
                binding.etTableNumber.setSelection(result.length)
                editing = false
            }
        })
    }

    private fun setupDatePicker() {
        binding.etDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker().build()
            picker.addOnPositiveButtonClickListener { date ->
                binding.etDate.setText(picker.headerText)
            }
            picker.show(parentFragmentManager, "date")
        }
    }

    private fun setupTimePicker() {
        binding.etTime.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour.toString().padStart(2, '0')
                val minute = picker.minute.toString().padStart(2, '0')
                binding.etTime.setText("$hour:$minute")
            }
            picker.show(parentFragmentManager, "time")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
