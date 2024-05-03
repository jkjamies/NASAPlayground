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
    var state: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
        private set

    fun getApod() {
        viewModelScope.launch {
            nasaApiServices.apodApi.getApod().collect { apodResponse ->
                apodResponse?.let { apod ->
                    state.updateAndGet { it.copy(apod = apod) }
                }
            }
        }
    }

    fun getNeos() {
        viewModelScope.launch {
            nasaApiServices.neosApi.getNeos().collect { neosResponse ->
                neosResponse?.let { neos ->
                    state.updateAndGet { it.copy(neos = neos) }
                }
            }
        }
    }
}
