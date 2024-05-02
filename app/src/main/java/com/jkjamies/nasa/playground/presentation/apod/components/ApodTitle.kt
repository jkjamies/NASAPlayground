package com.jkjamies.nasa.playground.presentation.apod.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme

@Composable
internal fun ApodTitle(date: String?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            text = "Astronomy Picture\nof the Day",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
        )
        date?.let {
            Text(text = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ApodTitlePreview() {
    NASAPlaygroundTheme {
        ApodTitle("2024-05-05")
    }
}
