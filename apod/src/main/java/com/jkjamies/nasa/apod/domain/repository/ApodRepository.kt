package com.jkjamies.nasa.apod.domain.repository

import com.jkjamies.nasa.apod.domain.models.Apod
import kotlinx.coroutines.flow.Flow

/**
 * Repository for Astronomy Picture of the Day
 */
internal interface ApodRepository {
    /**
     * Get the Astronomy Picture of the Day
     *
     * @return the Astronomy Picture of the Day as a [Flow] of [Apod]
     */
    suspend fun getApod(): Flow<Apod?>
}
