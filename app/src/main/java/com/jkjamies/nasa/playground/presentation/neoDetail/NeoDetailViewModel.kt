package com.jkjamies.nasa.playground.presentation.neoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jkjamies.nasa.playground.NasaApiServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class NeoDetailViewModel(
    private val nasaApiServices: NasaApiServices,
) : ViewModel() {
    var state: MutableStateFlow<NeoDetailUiState> = MutableStateFlow(NeoDetailUiState())
        private set

    fun getNeoById(neoId: String) {
        viewModelScope.launch {
            nasaApiServices.neosApi.getNeoById(neoId).collect { neoResponse ->
                neoResponse?.let { neo ->
                    state.updateAndGet { NeoDetailUiState(neo = neo) }
                }
            }
        }
    }
}
