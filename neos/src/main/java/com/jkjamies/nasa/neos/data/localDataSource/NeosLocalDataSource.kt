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
     * Save the [neos] Near Earth Objects of the week
     */
    suspend fun saveNeos(neos: NeosResponse)
}
