package com.jkjamies.nasa.playground.presentation.home.apod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.playground.presentation.shared.Title
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme

@Composable
internal fun ApodContent(apodState: Apod?) {
    var showDescription: Boolean by rememberSaveable { mutableStateOf(false) }

    apodState?.let { apod ->
        Column {
            Spacer(modifier = Modifier.height(16.dp))
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

@Preview(showBackground = true)
@Composable
private fun ApodContentPreview() {
    NASAPlaygroundTheme {
        ApodContent(apodState = null) // TODO: add apod back (removed for cleanliness)
    }
}
