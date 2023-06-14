package com.ofo.country_appsd.view.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class CustomSharedPreferences {

    companion object {

        private var sharedPreferences : SharedPreferences? = null
        private val PREFERENCE_TIME = "PREFERENCE_TIME"

        @Volatile private var instance : CustomSharedPreferences? = null

        private val lock = Any()
        operator fun invoke(context : Context) : CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: createSP(context).also {
                instance = it
            }

        }

        private fun createSP(context : Context) : CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return CustomSharedPreferences()
        }

    }

    fun saveTime (time : Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCE_TIME, time)
        }
    }

    fun getTime()= sharedPreferences?.getLong(PREFERENCE_TIME,0)
}