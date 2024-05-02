package com.jkjamies.nasa.playground.presentation.apod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.jkjamies.nasa.playground.presentation.apod.components.ApodExplaination
import com.jkjamies.nasa.playground.presentation.apod.components.ApodImage
import com.jkjamies.nasa.playground.presentation.apod.components.ApodTitle
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ApodContent(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    val viewModel = koinViewModel<ApodViewModel>()
    val uiState by viewModel.state.collectAsState()
    var showDescription: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getApod()
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            when (uiState) {
                is ApodState.Idle -> {
                    // TODO: loading state?
                }

                is ApodState.Success -> {
                    val apod = (uiState as ApodState.Success).apod
                    Column {
                        ApodTitle(date = apod.date)
                        ApodImage(url = apod.url, title = apod.title, copyright = apod.copyright)
                        apod.explanation?.let {
                            ApodExplaination(
                                clickEnabled = apod.error == null,
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

                is ApodState.Error -> {
                    // TODO: error state?
                }
            }
        }
    }
}

// TODO: render resolution
@Preview(showBackground = true)
@Composable
private fun ApodContentPreview() {
    NASAPlaygroundTheme {
        ApodContent()
    }
}
