package com.pinup.barapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentBlankBinding
import com.pinup.barapp.ui.MainActivity

class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

        cardsListener()

        binding.btnContact.setOnClickListener {
            (activity as? MainActivity)?.showBlur()
            HelpDialogFragment().show(parentFragmentManager, "HelpDialog")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
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