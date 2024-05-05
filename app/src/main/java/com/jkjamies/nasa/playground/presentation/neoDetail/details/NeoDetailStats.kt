package com.jkjamies.nasa.playground.presentation.neoDetail.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.neos.domain.models.NearEarthObject

@Composable
internal fun NeoDetailStats(neo: NearEarthObject) {
    // Calculate the average diameter of the NEO between the min and max values.
    val averageDiameter =
        (
            neo.estimated_diameter?.feet?.estimated_diameter_min?.plus(
                neo.estimated_diameter?.feet?.estimated_diameter_max ?: 0.0,
            )
        )?.div(2.0)

    val closeApproachData = neo.close_approach_data?.firstOrNull()
    neo.apply {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            NeoDetailText(detailTitle = "NEO ID", detailValue = "${neo.id}")
            NeoDetailText(detailTitle = "NEO Name", detailValue = "${neo.name}")
            NeoDetailText(
                detailTitle = "NEO Absolute Magnitude",
                detailValue = "${neo.absolute_magnitude_h}",
            )
            NeoDetailText(
                detailTitle = "NEO Estimated Diameter",
                detailValue = "$averageDiameter",
            )
            NeoDetailText(
                detailTitle = "NEO Is Potentially Hazardous",
                detailValue = "${neo.is_potentially_hazardous_asteroid}",
            )
            NeoDetailText(
                detailTitle = "NEO Close Approach Date",
                detailValue = "${closeApproachData?.close_approach_date}",
            )
            NeoDetailText(
                detailTitle = "NEO Miss Distance",
                detailValue = "${closeApproachData?.miss_distance?.miles} miles",
            )
            NeoDetailText(
                detailTitle = "NEO Relative Velocity",
                detailValue = "${closeApproachData?.relative_velocity?.miles_per_hour} mph",
            )
            NeoDetailText(
                detailTitle = "NEO Orbiting Body",
                detailValue = "${closeApproachData?.orbiting_body}",
            )
            NeoDetailText(
                detailTitle = "NEO Is Sentry Object",
                detailValue = "${neo.is_sentry_object}",
            )
        }
    }
}
