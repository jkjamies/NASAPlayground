package com.jkjamies.nasa.playground.presentation.neoDetail.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.playground.ui.theme.NASAPlaygroundTheme

@Composable
internal fun NeoDetailText(
    detailTitle: String,
    detailValue: String,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
    ) {
        // Text always white due to underlying lottie file
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "$detailTitle: ",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        // Text always white due to underlying lottie file
        Text(
            modifier = Modifier.align(Alignment.End),
            text = detailValue,
            color = Color.White,
        )
        HorizontalDivider()
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
private fun NeoDetailTextPreview() {
    NASAPlaygroundTheme {
        NeoDetailText(
            detailTitle = "Name",
            detailValue = "Near Earth Object's Name",
        )
    }
}
