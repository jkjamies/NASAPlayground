package com.jkjamies.nasa.apod.domain.models

import kotlinx.serialization.Serializable

/**
 * Astronomy Picture of the Day
 */
@Serializable
data class Apod(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val media_type: String?,
    val service_version: String?,
    val title: String?,
    val url: String?,
    val error: ApodError?,
)

/**
 * Error information for the Astronomy Picture of the Day
 */
@Serializable
data class ApodError(
    val code: String?,
    val msg: String?,
)
