package com.jkjamies.nasa.playground.presentation.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun PotentiallyHazardousIcon() {
    Icon(
        modifier = Modifier.padding(end = 16.dp),
        imageVector = Icons.Filled.Warning,
        tint = MaterialTheme.colorScheme.error,
        contentDescription = "Near Earth Object is potentially hazardous",
    )
}
