package com.pinup.barapp.ui.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.pinup.barapp.databinding.FragmentWelcomePinupBinding
import com.pinup.barapp.utils.SportBarNavigation.WELCOME_KEY
import com.pinup.barapp.utils.SportBarNavigation.getSharedPreferences
import com.pinup.barapp.utils.SportBarNavigation.launchNewFragmentWithoutBackstack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomePinupFragment : Fragment() {

    private var _binding: FragmentWelcomePinupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomePinupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding.nextMaterialButton.setOnClickListener {
            context?.getSharedPreferences()?.edit { putBoolean(WELCOME_KEY, true).apply() }
            parentFragmentManager.launchNewFragmentWithoutBackstack(HomePinupFragment())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}