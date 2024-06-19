package ru.blackmirrror.airtickets.data.storage

import android.content.Context
import android.content.SharedPreferences

class LastSearchSharedPreferences(
    context: Context
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SharedPreferencesConstants.PREF_NAME, Context.MODE_PRIVATE)

    var lastSearch: String?
        get() = sharedPreferences.getString(SharedPreferencesConstants.PREF_KEY_LAST_SEARCH, null)
        set(value) {
            sharedPreferences.edit().putString(SharedPreferencesConstants.PREF_KEY_LAST_SEARCH, value).apply()
        }
}