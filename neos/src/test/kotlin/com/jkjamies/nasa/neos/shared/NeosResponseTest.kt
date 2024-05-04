package com.jkjamies.nasa.neos.shared

import com.jkjamies.nasa.neos.domain.models.CloseApproachData
import com.jkjamies.nasa.neos.domain.models.Diameter
import com.jkjamies.nasa.neos.domain.models.Distance
import com.jkjamies.nasa.neos.domain.models.EstimatedDiameter
import com.jkjamies.nasa.neos.domain.models.FeedLinks
import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.models.ObjectLinks
import com.jkjamies.nasa.neos.domain.models.Velocity

val neosResponse =
    NeosResponse(
        date = "2024-05-03",
        links =
            FeedLinks(
                next = "https://example.com/next",
                prev = "https://example.com/prev",
                self = "https://example.com/self",
            ),
        element_count = 10,
        near_earth_objects =
            mapOf(
                "2024-05-03" to
                    listOf(
                        NearEarthObject(
                            links =
                                ObjectLinks(
                                    self = "https://example.com/neo/self",
                                ),
                            id = "1",
                            neo_reference_id = "123456",
                            name = "Example NEO",
                            nasa_jpl_url = "https://example.com/neo",
                            absolute_magnitude_h = 22.5,
                            estimated_diameter =
                                EstimatedDiameter(
                                    kilometers =
                                        Diameter(
                                            estimated_diameter_min = 100.0,
                                            estimated_diameter_max = 200.0,
                                        ),
                                    meters =
                                        Diameter(
                                            estimated_diameter_min = 100000.0,
                                            estimated_diameter_max = 200000.0,
                                        ),
                                    miles =
                                        Diameter(
                                            estimated_diameter_min = 62.137,
                                            estimated_diameter_max = 124.274,
                                        ),
                                    feet =
                                        Diameter(
                                            estimated_diameter_min = 328084.0,
                                            estimated_diameter_max = 656168.0,
                                        ),
                                ),
                            is_potentially_hazardous_asteroid = true,
                            close_approach_data =
                                listOf(
                                    CloseApproachData(
                                        close_approach_date = "2024-05-03",
                                        close_approach_date_full = "2024-May-03 14:21",
                                        epoch_date_close_approach = 1738276860000,
                                        relative_velocity =
                                            Velocity(
                                                kilometers_per_second = "25.6",
                                                kilometers_per_hour = "92160",
                                                miles_per_hour = "57231",
                                            ),
                                        miss_distance =
                                            Distance(
                                                astronomical = "0.025 AU",
                                                lunar = "9.8 LD",
                                                kilometers = "3,734,489",
                                                miles = "2,319,425",
                                            ),
                                        orbiting_body = "Earth",
                                    ),
                                ),
                            is_sentry_object = false,
                        ),
                    ),
            ),
    )
