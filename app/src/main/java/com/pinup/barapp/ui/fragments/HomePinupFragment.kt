package com.pinup.barapp.ui.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pinup.barapp.R
import com.pinup.barapp.databinding.FragmentPinupHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePinupFragment : Fragment() {

    private lateinit var binding: FragmentPinupHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPinupHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val navHost = childFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.selectedItemId = R.id.navigation_cart

    }
}