package com.jkjamies.nasa.neos.data.localDataSource

import com.jkjamies.nasa.neos.domain.models.NeosResponse

/**
 * Local data source for the Near Earth Objects of the week
 */
internal interface NeosLocalDataSource {
    /**
     * Get the Near Earth Objects of the week
     *
     * @return the Near Earth Objects of the week as [NeosResponse]
     */
    suspend fun getNeos(): NeosResponse?

    /**
     * Save the [neos] Near Earth Objects of the week with a tagged last fetch [cachingDate]
     */
    suspend fun saveNeos(
        neos: NeosResponse,
        cachingDate: String,
    )
}
