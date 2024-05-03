package com.jkjamies.nasa.neos.data.remoteDataSource

import com.jkjamies.nasa.neos.domain.models.NeosResponse

/**
 * Remote data source for the Near Earth Objects of the week
 */
internal interface NeosRemoteDataSource {
    /**
     * Get the Near Earth Objects for the week
     */
    suspend fun getNeos(): Result<NeosResponse?>
}
