package com.jkjamies.nasa.playground.presentation.neoDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jkjamies.nasa.playground.R
import com.jkjamies.nasa.playground.presentation.neoDetail.details.NeoDetailStats
import com.jkjamies.nasa.playground.presentation.shared.PotentiallyHazardousIcon
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NeoDetailContent(
    neoId: String,
    onBackClick: () -> Unit,
    viewModel: NeoDetailViewModel = koinViewModel<NeoDetailViewModel>(),
) {
    val uiState by viewModel.state.collectAsState()
    val earthAnimComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.earth_animation))
    val neoAnimComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.neo_animation))

    LaunchedEffect(Unit) {
        viewModel.getNeoById(neoId)
    }

    uiState.neo?.let { neo ->
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "${neo.name}") },
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    },
                    actions = {
                        if (neo.is_potentially_hazardous_asteroid == true) {
                            PotentiallyHazardousIcon()
                        }
                    },
                )
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
            ) {
                // This lottie file required a workaround to maintain the aspect ratio
                // as there contained large buffers of space on top and bottom
                val lottieWidth = earthAnimComposition?.bounds?.width()
                val lottieHeight = earthAnimComposition?.bounds?.height()
                val lottieAspectRatio =
                    lottieWidth?.toFloat()?.div(lottieHeight?.toFloat() ?: 1.0f) ?: 1.0f

                item {
                    LottieAnimation(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .aspectRatio(lottieAspectRatio),
                        composition = earthAnimComposition,
                        iterations = LottieConstants.IterateForever,
                    )

                    // Box allows the lottie to show under text
                    // NeoDetailStats is a column of text to encapsulate the data
                    // Each NeoDetailText composable is a row of data to format the text
                    Box {
                        LottieAnimation(
                            contentScale = ContentScale.FillBounds,
                            composition = neoAnimComposition,
                            iterations = LottieConstants.IterateForever,
                        )
                        NeoDetailStats(neo = neo)
                    }
                }
            }
        }
    }
}
