package com.paradox543.studentplanner.ui.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paradox543.studentplanner.ui.view.HomeScreen
import com.paradox543.studentplanner.ui.view.PanelA
import com.paradox543.studentplanner.ui.view.PanelB
import com.paradox543.studentplanner.ui.view.SettingsScreen
import com.paradox543.studentplanner.ui.view.TaskScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    Log.d("NavigationWindowSizeClass", "WindowSizeClass $windowSizeClass")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    Log.d("NavigationCurrentDestination", "CurrentDestination $route")

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            item(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = route == "home",
                onClick = { navController.navigate("home") },
            )
            item(
                icon = { Icon(Icons.Default.Accessibility, contentDescription = null) },
                label = { Text("Profile") },
                selected = route == "panelA",
                onClick = { navController.navigate("panelA") },
            )
            item(
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Profile") },
                selected = route == "panelB",
                onClick = { navController.navigate("panelB") },
            )
            item(
                icon = { Icon(Icons.Default.Task, "Tasks") },
                label = { Text("Tasks") },
                selected = route == "taskScreen",
                onClick = { navController.navigate("taskScreen") },
            )
            item(
                icon = { Icon(Icons.Default.Settings, "settings") },
                label = { Text("Settings") },
                selected = route == "settings",
                onClick = { navController.navigate("settings") },
            )
        },
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(navController)
            }
            composable("taskScreen") {
                TaskScreen()
            }
            composable("panelA") {
                PanelA(navController)
            }
            composable("panelB") {
                PanelB()
            }
            composable("settings") {
                SettingsScreen()
            }
        }
    }
}