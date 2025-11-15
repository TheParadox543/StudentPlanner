package com.paradox543.studentplanner.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paradox543.studentplanner.ui.view.HomeScreen
import com.paradox543.studentplanner.ui.view.SettingsScreen
import com.paradox543.studentplanner.ui.view.TaskScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
//    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
//    Log.d("NavigationWindowSizeClass", "WindowSizeClass $windowSizeClass")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    Log.d("NavigationCurrentDestination", "CurrentDestination $route")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Student Planner") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous Page",
                        )
                    }
                },
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = route == "home",
                    onClick = {
                        navController.navigate("home") {
                            navController.popBackStack("home", false)
                        }
                    },
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Task, "Tasks") },
                    label = { Text("Tasks") },
                    selected = route == "taskScreen",
                    onClick = { navController.navigate("taskScreen") },
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, "settings") },
                    label = { Text("Settings") },
                    selected = route == "settings",
                    onClick = { navController.navigate("settings") },
                )
            }
        },
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxHeight()
                .padding(innerPadding),
        ) {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen()
                }
                composable("taskScreen") {
                    TaskScreen()
                }
                composable("task/{task_id}") { navBackStackEntry ->
                    val route = navBackStackEntry.arguments?.getString("task_id") ?: "error"
                    SpecificTaskID()
                }
                composable("settings") {
                    SettingsScreen()
                }
            }
        }
    }
}