package com.jkjamies.nasa.playground.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jkjamies.nasa.playground.presentation.navigation.HomeRoute
import com.jkjamies.nasa.playground.presentation.navigation.homeScreen
import com.jkjamies.nasa.playground.presentation.navigation.navigateBackFromNeoDetail
import com.jkjamies.nasa.playground.presentation.navigation.navigateToNeoDetail
import com.jkjamies.nasa.playground.presentation.navigation.neoDetailScreen

@Composable
internal fun NasaPlaygroundNavHost() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()

        NavHost(
            modifier =
                Modifier.fillMaxWidth().padding(innerPadding),
            navController = navController,
            startDestination = HomeRoute,
        ) {
            // Navigation destination for the home screen.
            // Navigate to the Neo Detail screen when a Neo is clicked using the ID as the argument.
            homeScreen { neoId ->
                navController.navigateToNeoDetail(neoId)
            }
            // Navigation destination for the Neo Detail screen.
            neoDetailScreen {
                navController.navigateBackFromNeoDetail()
            }
        }
    }
}
