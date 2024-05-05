package com.jkjamies.nasa.playground.presentation.home.neos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jkjamies.nasa.neos.domain.models.CloseApproachData
import com.jkjamies.nasa.neos.domain.models.Diameter
import com.jkjamies.nasa.neos.domain.models.Distance
import com.jkjamies.nasa.neos.domain.models.EstimatedDiameter
import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.models.ObjectLinks
import com.jkjamies.nasa.neos.domain.models.Velocity
import com.jkjamies.nasa.playground.presentation.shared.PotentiallyHazardousIcon

@Composable
internal fun NeoCard(
    neo: NearEarthObject,
    onNeoClick: (String) -> Unit,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        onClick = { neo.id?.let { onNeoClick(it) } },
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
                    PotentiallyHazardousIcon()
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

@Composable
@Preview(showBackground = true)
private fun NeoCardHazardousPreview() {
    NeoCard(
        neo =
            NearEarthObject(
                links =
                    ObjectLinks(
                        self = "https://api.nasa.gov/neo/rest/v1/neo/1?api_key=DEMO_KEY",
                    ),
                nasa_jpl_url = "testurl",
                neo_reference_id = "1",
                id = "1",
                name = "2021 AB",
                is_potentially_hazardous_asteroid = true,
                absolute_magnitude_h = 9999.9999,
                estimated_diameter =
                    EstimatedDiameter(
                        feet =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        kilometers =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        meters =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        miles =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                    ),
                close_approach_data =
                    listOf(
                        CloseApproachData(
                            close_approach_date = "2021-01-01",
                            close_approach_date_full = "2021-01-01",
                            epoch_date_close_approach = 1609459200000,
                            relative_velocity =
                                Velocity(
                                    kilometers_per_second = "1.0",
                                    kilometers_per_hour = "3600.0",
                                    miles_per_hour = "2236.94",
                                ),
                            miss_distance =
                                Distance(
                                    astronomical = "0.0000001",
                                    lunar = "0.0000001",
                                    kilometers = "0.0000001",
                                    miles = "0.0000001",
                                ),
                            orbiting_body = "Earth",
                        ),
                    ),
                is_sentry_object = false,
            ),
        onNeoClick = { },
    )
}

@Composable
@Preview(showBackground = true)
private fun NeoCardNotHazardousPreview() {
    NeoCard(
        neo =
            NearEarthObject(
                links =
                    ObjectLinks(
                        self = "https://api.nasa.gov/neo/rest/v1/neo/1?api_key=DEMO_KEY",
                    ),
                nasa_jpl_url = "testurl",
                neo_reference_id = "1",
                id = "1",
                name = "2021 AB",
                is_potentially_hazardous_asteroid = false,
                absolute_magnitude_h = 9999.9999,
                estimated_diameter =
                    EstimatedDiameter(
                        feet =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        kilometers =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        meters =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                        miles =
                            Diameter(
                                estimated_diameter_min = 1.0,
                                estimated_diameter_max = 2.0,
                            ),
                    ),
                close_approach_data =
                    listOf(
                        CloseApproachData(
                            close_approach_date = "2021-01-01",
                            close_approach_date_full = "2021-01-01",
                            epoch_date_close_approach = 1609459200000,
                            relative_velocity =
                                Velocity(
                                    kilometers_per_second = "1.0",
                                    kilometers_per_hour = "3600.0",
                                    miles_per_hour = "2236.94",
                                ),
                            miss_distance =
                                Distance(
                                    astronomical = "0.0000001",
                                    lunar = "0.0000001",
                                    kilometers = "0.0000001",
                                    miles = "0.0000001",
                                ),
                            orbiting_body = "Earth",
                        ),
                    ),
                is_sentry_object = true,
            ),
        onNeoClick = { },
    )
}
