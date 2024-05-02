package com.jkjamies.nasa.playground.presentation.apod.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ApodExplanation(
    clickEnabled: Boolean,
    showDescription: Boolean,
    onShowDescriptionClick: () -> Unit,
) {
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    )
    Row(
        modifier =
            Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable(
                enabled = clickEnabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onShowDescriptionClick,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = if (showDescription) "Hide details" else "Learn about today's image")
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = if (showDescription) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = "Scroll down",
        )
    }
    HorizontalDivider(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
    )
}
