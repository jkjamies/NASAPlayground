package com.jkjamies.nasa.neos.domain.repository

import com.jkjamies.nasa.neos.domain.models.NeosResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository for Near Earth Objects of the week
 */
internal interface NeosRepository {
    /**
     * Get the Near Earth Objects of the week
     *
     * @return the Near Earth Objects of the week as a [Flow] of [NeosResponse]
     */
    suspend fun getNeos(): Flow<NeosResponse?>
}
