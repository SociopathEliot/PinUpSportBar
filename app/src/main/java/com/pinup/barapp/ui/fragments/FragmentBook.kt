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
        setupValidationWatchers()
        validateForm()

        binding.btnConfirm.setOnClickListener {
            val reservation = Reservation(
                fullName = binding.etFullName.text.toString(),
                phone = binding.etCountryCode.text.toString() + binding.etPhoneNumber.text.toString(),
                date = binding.etDate.text.toString(),
                time = binding.etTime.text.toString(),
                tableNumber = binding.etTableNumber.text.toString()
            )
            viewModel.addReservation(reservation)
            findNavController().navigate(R.id.reservationQrFragment)
        }
        binding.btnShowMap.setOnClickListener {
            binding.footerBlock.visibility = View.GONE
            binding.mapImg.visibility = View.VISIBLE
            binding.btnCloseMap.visibility = View.VISIBLE
        }

        binding.btnCloseMap.setOnClickListener {
            binding.mapImg.visibility = View.GONE
            binding.btnCloseMap.visibility = View.GONE
            binding.footerBlock.visibility = View.VISIBLE
            binding.bgImage.visibility = View.VISIBLE
            binding.btnConfirm.visibility = View.VISIBLE

        }
    }

    private fun setupValidationWatchers() {
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateForm()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        binding.etFullName.addTextChangedListener(watcher)
        binding.etPhoneNumber.addTextChangedListener(watcher)
        binding.etDate.addTextChangedListener(watcher)
        binding.etTime.addTextChangedListener(watcher)
        binding.etTableNumber.addTextChangedListener(watcher)
    }


    private fun validateForm() {
        val fullName = binding.etFullName.text.toString().trim()
        val phone = binding.etPhoneNumber.text.toString().filter { it.isDigit() }
        val date = binding.etDate.text.toString().trim()
        val time = binding.etTime.text.toString().trim()
        val tableNumber = binding.etTableNumber.text.toString().removePrefix("Table Number - #").trim()

        val isFormValid = fullName.isNotEmpty()
                && phone.length == 10
                && date.isNotEmpty()
                && time.isNotEmpty()
                && tableNumber.isNotEmpty()

        binding.btnConfirm.isEnabled = isFormValid
        binding.btnConfirm.alpha = if (isFormValid) 1f else 0.5f
    }

    private fun setupPhoneMask() {
            binding.etPhoneNumber.addTextChangedListener(object : TextWatcher {
                private var isEditing = false

                override fun afterTextChanged(s: Editable?) {
                    if (isEditing || s == null) return
                    isEditing = true

                    val digits = s.filter { it.isDigit() }.take(10)
                    val formatted = StringBuilder()

                    formatted.append("(")
                    for (i in 0 until 3) {
                        formatted.append(if (i < digits.length) digits[i] else '_')
                    }

                    formatted.append(") ")
                    for (i in 3 until 6) {
                        formatted.append(if (i < digits.length) digits[i] else '_')
                    }

                    formatted.append("-")
                    for (i in 6 until 10) {
                        formatted.append(if (i < digits.length) digits[i] else '_')
                    }

                    binding.etPhoneNumber.setText(formatted)
                    binding.etPhoneNumber.setSelection(formatted.length.coerceAtMost(binding.etPhoneNumber.text.length))
                    isEditing = false
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
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
