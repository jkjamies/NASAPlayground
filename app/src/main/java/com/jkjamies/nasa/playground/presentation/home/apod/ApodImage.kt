package com.jkjamies.nasa.playground.presentation.home.apod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme

@Composable
internal fun ApodImage(
    url: String?,
    title: String?,
    date: String?,
) {
    if (url != null) {
        Box(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 8.dp),
                model = url,
                contentDescription = title,
                loading = { CircularProgressIndicator() },
            )
            date?.let {
                Text(
                    modifier = Modifier.align(Alignment.TopStart),
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    } else {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Failed to get today's image",
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                textAlign = TextAlign.Center,
                text = "Image failed to load.\nTry again later.",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ApodImagePreview() {
    NASAPlaygroundTheme {
        ApodImage(
            url = "https://apod.nasa.gov/apod/image/2203/NGC2244_Hubble_960.jpg",
            title = "NGC 2244: A Star Cluster in the Rosette Nebula",
            date = "2024-05-01",
        )
    }
}
