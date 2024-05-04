package com.jkjamies.nasa.playground.presentation.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.playground.presentation.home.apod.ApodContent
import com.jkjamies.nasa.playground.presentation.home.neos.NeoCard
import com.jkjamies.nasa.playground.presentation.shared.Title
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeContent(onNeoDetailsClick: (String) -> Unit) {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getApod()
        viewModel.getNeos()
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            ApodContent(apodState = uiState.apod)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Title("Near Earth Objects")
        }
        items(uiState.neos?.near_earth_objects?.flatMap { it.value } ?: emptyList()) { neo ->
            NeoCard(neo = neo, onNeoClick = onNeoDetailsClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// TODO: render resolution
@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    NASAPlaygroundTheme {
        HomeContent(onNeoDetailsClick = { })
    }
}
