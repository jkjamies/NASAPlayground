package com.jkjamies.nasa.apod.data.remoteDataSource

import com.jkjamies.nasa.apod.domain.models.Apod

/**
 * Remote data source for the Astronomy Picture of the Day
 */
internal interface ApodRemoteDataSource {
    /**
     * Get the Astronomy Picture of the Day
     *
     * @return the Astronomy Picture of the Day as a [Result] of [Apod]
     */
    suspend fun getApod(): Result<Apod?>
}
