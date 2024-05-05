package com.jkjamies.nasa.playground.presentation.neoDetail

import com.jkjamies.nasa.neos.domain.models.NearEarthObject

/**
 * The state of the Neo Detail Screen UI.
 */
internal data class NeoDetailUiState(
    /** The Near Earth Object. */
    var neo: NearEarthObject? = null,
)
