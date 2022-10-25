package com.shamaich.animalsr.util

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferencesHelper(context: Context) {
    private val PREF_API_KEY = "Api key"

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

}