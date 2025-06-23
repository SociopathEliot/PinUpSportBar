package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentScheduleBinding
import com.pinup.barapp.ui.adapters.ScheduleAdapter
import com.pinup.barapp.ui.viewmodels.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScheduleViewModel by viewModels()
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ScheduleAdapter()
        binding.recyclerSchedule.adapter = adapter
        val adapter1 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months_array,
            R.layout.item_spinner_month
        )

        viewModel.matches.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            binding.tvEmptySchedule.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
            Log.d("ScheduleViewModel", "Loaded matches: ${list.size}")
        }

        viewModel.loadMatches()


        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = adapter1

    }


}
