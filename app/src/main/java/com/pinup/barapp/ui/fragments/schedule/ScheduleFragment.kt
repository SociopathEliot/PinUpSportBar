package com.pinup.barapp.ui.fragments.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentScheduleBinding
import com.pinup.barapp.ui.adapters.ScheduleAdapter
import com.pinup.barapp.ui.fragments.schedule.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.Month

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
        binding.spinnerMonth.setSelection(LocalDate.now().monthValue - 1)
        var isFirst = true
        binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (isFirst) { isFirst = false; return }
                val month = Month.of(position + 1)
                viewModel.loadMatchesForMonth(month)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}