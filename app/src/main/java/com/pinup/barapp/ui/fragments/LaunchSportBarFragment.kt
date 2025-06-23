package com.pinup.barapp.ui.fragments

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.pinup.barapp.databinding.FragmentSportBarPrivacyBinding
import com.pinup.barapp.databinding.FragmentSportBarSplashBinding
import com.pinup.barapp.utils.SportBarNavigation.DEFAULT_DOMAIN_LINK
import com.pinup.barapp.utils.SportBarNavigation.SAVED_OFFER_KEY
import com.pinup.barapp.utils.SportBarNavigation.USER_STATUS_FLAG
import com.pinup.barapp.utils.SportBarNavigation.ONBOARDING_SHOWN_KEY
import com.pinup.barapp.utils.SportBarNavigation.getSportBarPreferences
import com.pinup.barapp.utils.SportBarNavigation.launchSportBarFragmentWithoutBackstack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LaunchSportBarFragment : Fragment() {

    private lateinit var splashBinding: FragmentSportBarSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        splashBinding = FragmentSportBarSplashBinding.inflate(inflater, container, false)
        return splashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        handleAppInitialization()

        android.os.Handler(Looper.getMainLooper()).postDelayed({
        }, 5000)


    }

    private fun navigateToProjectFragment() {
        val launchedBefore = context?.getSportBarPreferences()?.getBoolean(ONBOARDING_SHOWN_KEY, false) == true
        if (launchedBefore) {
            parentFragmentManager.launchSportBarFragmentWithoutBackstack(HomePinupFragment())
        } else {
            parentFragmentManager.launchSportBarFragmentWithoutBackstack(WelcomePinupFragment())
        }
    }

    private fun handleAppInitialization() {
        val offerLink = context?.getSportBarPreferences()?.getString(SAVED_OFFER_KEY, "") ?: ""
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
            parentFragmentManager.launchSportBarFragmentWithoutBackstack(
                WelcomePinupFragment())
        } else {
            navigateToProjectFragment()
        }
    }

    private fun saveLink(offerLink: String) {
        context?.getSportBarPreferences()?.edit { putString(SAVED_OFFER_KEY, offerLink)?.apply() }
    }

    private fun saveUserFalse() {
        context?.getSportBarPreferences()?.edit { putBoolean(USER_STATUS_FLAG, false)?.apply() }
    }

    private fun isUser(): Boolean {
        return context?.getSportBarPreferences()?.getBoolean(USER_STATUS_FLAG, true) ?: true
    }
}