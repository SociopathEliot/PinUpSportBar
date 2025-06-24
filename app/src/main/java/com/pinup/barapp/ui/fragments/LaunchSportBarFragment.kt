package com.pinup.barapp.ui.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.pinup.barapp.databinding.FragmentSportBarSplashBinding
import com.pinup.barapp.utils.SportBarNavigation.DEFAULT_DOMAIN_LINK
import com.pinup.barapp.utils.SportBarNavigation.MAIN_OFFER_LINK_KEY
import com.pinup.barapp.utils.SportBarNavigation.USER_STATUS_KEY
import com.pinup.barapp.utils.SportBarNavigation.WELCOME_KEY
import com.pinup.barapp.utils.SportBarNavigation.getSharedPreferences
import com.pinup.barapp.utils.SportBarNavigation.launchNewFragmentWithoutBackstack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchSportBarFragment : Fragment() {

    private lateinit var binding: FragmentSportBarSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSportBarSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        handleAppInitialization()

        //todo splash animation logic
    }

    private fun navigateToProjectFragment() {
        val launchedBefore = context?.getSharedPreferences()?.getBoolean(WELCOME_KEY, false) == true
        if (launchedBefore) {
            parentFragmentManager.launchNewFragmentWithoutBackstack(HomePinupFragment())
        } else {
            parentFragmentManager.launchNewFragmentWithoutBackstack(WelcomePinupFragment())
        }
    }

    private fun handleAppInitialization() {
        val offerLink = context?.getSharedPreferences()?.getString(MAIN_OFFER_LINK_KEY, "") ?: ""
        if (!isUser()) {
            navigateToProjectFragment()
        } else if (offerLink.isNotEmpty()) {
            navigateBasedOnOfferLink(offerLink)
        } else {
            getLinks()
        }
    }

    private fun getLinks() {
        val queue = Volley.newRequestQueue(context)
        val url = DEFAULT_DOMAIN_LINK

        val stringRequest = object : StringRequest(Method.GET, url, Response.Listener { offerLink ->

            if (offerLink.isNullOrEmpty()) {
                saveUserFalse()
                navigateBasedOnOfferLink(offerLink)
            } else {
                saveLink(offerLink)
                navigateBasedOnOfferLink(offerLink)
            }
        }, Response.ErrorListener {
            navigateBasedOnOfferLink("")

        }) {}

        queue.add(stringRequest)
    }

    private fun navigateBasedOnOfferLink(offerLink: String) {
        if (offerLink.isNotEmpty()) {
            parentFragmentManager.launchNewFragmentWithoutBackstack(PrivacyPolicyFragment(offerLink))
        } else {
            navigateToProjectFragment()
        }
    }

    private fun saveLink(offerLink: String) {
        context?.getSharedPreferences()?.edit { putString(MAIN_OFFER_LINK_KEY, offerLink)?.apply() }
    }

    private fun saveUserFalse() {
        context?.getSharedPreferences()?.edit { putBoolean(USER_STATUS_KEY, false)?.apply() }
    }

    private fun isUser(): Boolean {
        return context?.getSharedPreferences()?.getBoolean(USER_STATUS_KEY, true) ?: true
    }
}