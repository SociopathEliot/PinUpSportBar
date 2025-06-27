package com.pinup.barapp.ui.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentBlankBinding
import com.pinup.barapp.ui.MainActivity
import com.pinup.barapp.ui.fragments.HelpDialogFragment

class MainScreen : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardsListener()

        binding.btnContact.setOnClickListener {
            (activity as? MainActivity)?.showBlur()
            HelpDialogFragment().show(parentFragmentManager, "HelpDialog")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cardsListener() {
        binding.menuCard.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_menuFragment)
        }
        binding.cart.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_basketFragment)
        }
        binding.scheduleCard.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_scheduleFragment)
        }
        binding.bookingCard.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_fragmentBook)
        }
        binding.eventCard.setOnClickListener {
            findNavController().navigate(R.id.action_blankFragment_to_eventFragment)
        }

    }
}