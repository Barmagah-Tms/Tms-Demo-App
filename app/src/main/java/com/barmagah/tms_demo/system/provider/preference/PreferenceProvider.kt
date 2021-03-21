package com.barmagah.tms_demo.system.provider.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


abstract class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext

    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    @SuppressLint("CommitPrefEdits")
    protected val preferencesEditor = preferences.edit()!!
}