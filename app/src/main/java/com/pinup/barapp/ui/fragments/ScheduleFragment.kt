package com.pinup.barapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.matches.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
            Log.d("ScheduleViewModel", "Loaded matches: ${list.size}")

        }
        viewModel.loadMatches()
    }
}
