package com.jkjamies.nasa.neos.domain.models

import kotlinx.serialization.Serializable

/**
 * The Near Earth Objects of the week.
 */
@Serializable
data class NeosResponse(
    val links: FeedLinks?,
    val element_count: Int?,
    val near_earth_objects: Map<String, List<NearEarthObject>>?,
)

/**
 * The links for the Near Earth Objects feed.
 */
@Serializable
data class FeedLinks(
    val next: String?,
    val prev: String?,
    val self: String?,
)

/**
 * A Near Earth Object.
 */
@Serializable
data class NearEarthObject(
    val links: ObjectLinks?,
    val id: String?,
    val neo_reference_id: String?,
    val name: String?,
    val nasa_jpl_url: String?,
    val absolute_magnitude_h: Double?,
    val estimated_diameter: EstimatedDiameter?,
    val is_potentially_hazardous_asteroid: Boolean?,
    val close_approach_data: List<CloseApproachData>?,
    val is_sentry_object: Boolean?,
)

/**
 * The links for a Near Earth Object.
 */
@Serializable
data class ObjectLinks(
    val self: String?,
)

/**
 * The estimated diameter of a Near Earth Object.
 */
@Serializable
data class EstimatedDiameter(
    val kilometers: Diameter?,
    val meters: Diameter?,
    val miles: Diameter?,
    val feet: Diameter?,
)

/**
 * The diameter of a Near Earth Object.
 */
@Serializable
data class Diameter(
    val estimated_diameter_min: Double?,
    val estimated_diameter_max: Double?,
)

/**
 * The close approach data for a Near Earth Object.
 */
@Serializable
data class CloseApproachData(
    val close_approach_date: String?,
    val close_approach_date_full: String?,
    val epoch_date_close_approach: Long?,
    val relative_velocity: Velocity?,
    val miss_distance: Distance?,
    val orbiting_body: String?,
)

/**
 * The velocity of a Near Earth Object.
 */
@Serializable
data class Velocity(
    val kilometers_per_second: String?,
    val kilometers_per_hour: String?,
    val miles_per_hour: String?,
)

/**
 * The distance of a Near Earth Object.
 */
@Serializable
data class Distance(
    val astronomical: String?,
    val lunar: String?,
    val kilometers: String?,
    val miles: String?,
)
