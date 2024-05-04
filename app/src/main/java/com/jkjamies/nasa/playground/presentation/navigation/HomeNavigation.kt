package com.jkjamies.nasa.playground.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jkjamies.nasa.playground.presentation.home.HomeContent

/** The route for the Neo Detail screen. */
internal const val HomeRoute = "homeRoute"

/**
 * Adds the Neo Detail screen to the navigation graph.
 */
internal fun NavGraphBuilder.homeScreen(onNeoDetailClick: (String) -> Unit) {
    composable(
        route = HomeRoute,
    ) {
        HomeContent(onNeoDetailsClick = onNeoDetailClick)
    }
}
