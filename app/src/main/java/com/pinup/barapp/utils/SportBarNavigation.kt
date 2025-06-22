package com.pinup.barapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pinup.barapp.R

object SportBarNavigation {
    const val DEFAULT_PP_LINK = "https://sites.google.com/view/examplesampleprivacypolicy"
    const val DEFAULT_TOS_LINK = "https://sites.google.com/view/examplesampletermsofuse"
    const val DEFAULT_DOMAIN_LINK = "https://pastebin.com/raw/q1CQaY4k"
    const val SAVED_OFFER_KEY = "main_offer_link"
    const val USER_STATUS_FLAG = "user_status"
    const val ONBOARDING_SHOWN_KEY = "welcome"
    private const val SHARED_PREFERENCES_KEY = "example_sample_shared_preferences"

    fun Context.getSportBarPreferences(): SharedPreferences {
        return this.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun FragmentManager.launchSportBarFragment(fragment: Fragment) {
        this.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun FragmentManager.launchSportBarFragmentWithoutBackstack(fragment: Fragment) {
        this.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
        }
    }
}