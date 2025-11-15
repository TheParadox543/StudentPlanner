package com.paradox543.studentplanner.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsDataStore by preferencesDataStore(name = "settings")

class SettingsRepository(
    private val context: Context,
) {
    private val dataStore = context.settingsDataStore
    private val theme = stringPreferencesKey("theme_key")
    private val startScreen = stringPreferencesKey("start_screen")

    fun getTheme(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[theme] ?: "auto"
        }

    suspend fun setTheme(themeValue: String) {
        dataStore.edit { preferences ->
            preferences[theme] = themeValue
        }
    }

    fun getStartScreen(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[startScreen] ?: "home"
        }

    suspend fun setStartScreen(startScreen: String) {
        dataStore.edit { preferences ->
            preferences[this.startScreen] = startScreen
        }
    }
}