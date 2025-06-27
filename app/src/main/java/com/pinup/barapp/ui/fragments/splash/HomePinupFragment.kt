package com.pinup.barapp.ui.fragments.splash

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentPinupHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePinupFragment : Fragment() {

    private var _binding: FragmentPinupHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPinupHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val navHost = childFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHost.navController
        val bottomDestinations = setOf(
            R.id.blankFragment,
            R.id.menuFragment,
            R.id.scheduleFragment,
            R.id.eventFragment,
            R.id.fragmentBook
        )
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.selectedItemId = R.id.blankFragment

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.blankFragment -> {
                    navController.popBackStack(R.id.blankFragment, false)
                    true
                }
                else -> NavigationUI.onNavDestinationSelected(item, navController)
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.blankFragment,
                R.id.basketFragment,
                R.id.QRFragment,
                R.id.reservationQrFragment -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentId = navController.currentDestination?.id
                if (currentId in bottomDestinations) {
                    if (currentId != R.id.blankFragment) {
                        navController.popBackStack(R.id.blankFragment, false)
                    } else {
                        requireActivity().finish()
                    }
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}