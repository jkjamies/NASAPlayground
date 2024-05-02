package com.jkjamies.nasa.playground.presentation.apod

import com.jkjamies.nasa.apod.domain.models.Apod

sealed class ApodState {
    object Idle : ApodState()

    data class Success(val apod: Apod) : ApodState()

    data class Error(val message: String) : ApodState()
}
