package com.pinup.barapp.ui.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.pinup.barapp.databinding.FragmentWelcomePinupBinding
import com.pinup.barapp.utils.SportBarNavigation.ONBOARDING_SHOWN_KEY
import com.pinup.barapp.utils.SportBarNavigation.getSportBarPreferences
import com.pinup.barapp.utils.SportBarNavigation.launchSportBarFragmentWithoutBackstack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomePinupFragment : Fragment() {

    private lateinit var binding: FragmentWelcomePinupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePinupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //todo welcome fragment logic
        binding.nextMaterialButton.setOnClickListener {
            context?.getSportBarPreferences()?.edit { putBoolean(ONBOARDING_SHOWN_KEY, true).apply() }
            parentFragmentManager.launchSportBarFragmentWithoutBackstack(HomePinupFragment())
        }

    }
}