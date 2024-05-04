package com.jkjamies.nasa.playground.presentation.home

import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.neos.domain.models.NeosResponse

/**
 * The state of the Home Screen UI.
 */
internal data class HomeUiState(
    /** The apod object of the day. */
    var apod: Apod? = null,
    /** The neo objects of the week. */
    var neos: NeosResponse? = null,
)
