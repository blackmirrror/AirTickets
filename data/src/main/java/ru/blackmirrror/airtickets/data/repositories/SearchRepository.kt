package ru.blackmirrror.airtickets.data.repositories

import ru.blackmirrror.airtickets.data.sharedPrefs.LastSearchSharedPreferences

class SearchRepository(
    private val lastSearchSharedPreferences: LastSearchSharedPreferences
) {

    fun getLastSearch(): String? {
        return lastSearchSharedPreferences.lastSearch
    }

    fun saveLastSearch(word: String) {
        lastSearchSharedPreferences.lastSearch = word
    }
}