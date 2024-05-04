package com.jkjamies.nasa.playground.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jkjamies.nasa.playground.presentation.neoDetail.NeoDetailContent

/** The key for the Neo ID argument. */
private const val NEO_ID_KEY = "neoId"

/** The argument for the Neo ID. */
private const val NEO_ID_ARG = "{$NEO_ID_KEY}"

/** The route for the Neo Detail screen. */
internal const val NeoDetailRoute = "neoDetailRoute/$NEO_ID_ARG"

/**
 * Adds the Neo Detail screen to the navigation graph.
 */
internal fun NavGraphBuilder.neoDetailScreen() {
    composable(
        route = NeoDetailRoute,
    ) {
        NeoDetailContent()
    }
}

/**
 * Navigates to the Neo Detail screen.
 */
internal fun NavController.navigateToNeoDetail(neoId: String) {
    this.navigate(NeoDetailRoute.replace(NEO_ID_ARG, neoId))
}
