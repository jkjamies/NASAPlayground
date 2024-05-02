package com.jkjamies.nasa.playground.presentation.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkjamies.nasa.playground.NasaApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class ApodViewModel(
    private val nasaApiServices: NasaApiServices,
) : ViewModel() {
    var state: MutableStateFlow<ApodState> = MutableStateFlow(ApodState.Idle)
        private set

    fun getApod() {
        viewModelScope.launch {
            nasaApiServices.apodApi.getApod().collect { apod ->
                apod?.let {
                    state.updateAndGet { ApodState.Success(apod = apod) }
                } ?: run {
                    state.updateAndGet { ApodState.Error(message = "No Picture Today") }
                }
            }
        }
    }
}
