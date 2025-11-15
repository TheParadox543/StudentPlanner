package com.paradox543.studentplanner.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paradox543.studentplanner.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    private val _theme = MutableStateFlow("auto")
    val theme = _theme.asStateFlow()

    private val _startScreen = MutableStateFlow("home")
    val startScreen = _startScreen.asStateFlow()

    init {
        viewModelScope.launch {
            settingsRepository.getTheme().collect {
                _theme.value = it
            }
            settingsRepository.getStartScreen().collect {
                _startScreen.value = it
            }
        }
    }

    fun setTheme(enabled: String) {
        viewModelScope.launch {
            settingsRepository.setTheme(enabled)
        }
    }

    fun setHomeScreen(choice: String) {
        viewModelScope.launch {
            settingsRepository.setStartScreen(choice)
        }
    }
}
