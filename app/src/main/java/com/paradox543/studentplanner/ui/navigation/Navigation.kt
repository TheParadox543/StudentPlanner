package com.paradox543.studentplanner.ui.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paradox543.studentplanner.ui.view.HomeScreen
import com.paradox543.studentplanner.ui.view.PanelA
import com.paradox543.studentplanner.ui.view.PanelB
import com.paradox543.studentplanner.ui.view.TaskScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    var currentDestination by rememberSaveable { mutableStateOf("") }
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    Log.d("NavigationWindowSizeClass", "WindowSizeClass $windowSizeClass")
    val route = navController.currentBackStackEntry?.destination?.route
    LaunchedEffect(route) {
        currentDestination = route ?: "Error"
    }
    Log.d("NavigationCurrentDestination", "CurrentDestination $currentDestination")

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            item(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") },
                selected = currentDestination == "home",
                onClick = { navController.navigate("home") },
            )
            item(
                icon = { Icon(Icons.Default.Accessibility, contentDescription = null) },
                label = { Text("Profile") },
                selected = currentDestination == "panelA",
                onClick = { navController.navigate("panelA") },
            )
            item(
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Profile") },
                selected = currentDestination == "profile",
                onClick = { navController.navigate("panelB") },
            )
            item(
                icon = { Icon(Icons.Default.Task, "Tasks") },
                label = { Text("Tasks") },
                selected = currentDestination == "tasks",
                onClick = { navController.navigate("taskScreen") },
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
        }
    }
}