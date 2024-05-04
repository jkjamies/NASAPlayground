package com.jkjamies.nasa.playground.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.playground.presentation.home.apod.ApodExplanation
import com.jkjamies.nasa.playground.presentation.home.apod.ApodImage
import com.jkjamies.nasa.playground.presentation.home.neos.NeoCard
import com.jkjamies.nasa.playground.presentation.shared.Title
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeContent(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState by viewModel.state.collectAsState()
    var showDescription: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getApod()
        viewModel.getNeos()
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            uiState.apod?.let { apod ->
                Column {
                    Title("Astronomy Picture\nof the Day")
                    ApodImage(
                        url = apod.url,
                        title = apod.title,
                        date = apod.date,
                    )
                    apod.explanation?.let {
                        ApodExplanation(
                            clickEnabled = apod.error?.msg == null && apod.error?.code == null,
                            showDescription = showDescription,
                        ) {
                            showDescription = !showDescription
                        }
                    }
                    AnimatedVisibility(visible = showDescription) {
                        Text(
                            text = apod.explanation ?: "Unknown Error Occurred",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Title("Near Earth Objects")
        }
        items(uiState.neos?.near_earth_objects?.flatMap { it.value } ?: emptyList()) { neo ->
            NeoCard(neo)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// TODO: render resolution
@Preview(showBackground = true)
@Composable
private fun ApodContentPreview() {
    NASAPlaygroundTheme {
        HomeContent()
    }
}
