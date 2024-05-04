package com.jkjamies.nasa.playground.presentation.home.neos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.neos.domain.models.NearEarthObject

@Composable
internal fun NeoCard(neo: NearEarthObject) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        onClick = {},
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    text = "Name: ${neo.name ?: ""}",
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = "Approach Date: ${neo.close_approach_data?.firstOrNull()?.close_approach_date ?: ""}",
                )
                if (neo.is_potentially_hazardous_asteroid == true) {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                        text = "Potentially Hazardous",
                        color = MaterialTheme.colorScheme.error,
                    )
                } else {
                    Spacer(modifier = Modifier.padding(bottom = 24.dp))
                }
            }
            Row {
                if (neo.is_potentially_hazardous_asteroid == true) {
                    Icon(
                        modifier = Modifier.padding(end = 16.dp),
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "View Near Earth Object Details",
                    )
                }
                Icon(
                    modifier = Modifier.padding(end = 16.dp),
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "View Near Earth Object Details",
                )
            }
        }
    }
}
