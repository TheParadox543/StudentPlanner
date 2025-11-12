package com.paradox543.studentplanner.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun PanelB() {
    var entry by rememberSaveable { mutableStateOf("") }
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("Welcome to panel B.")
            TextField(
                value = entry,
                onValueChange = { entry = it },
            )
        }
    }
}