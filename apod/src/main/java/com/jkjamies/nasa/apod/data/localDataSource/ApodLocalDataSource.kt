package com.jkjamies.nasa.apod.data.localDataSource

import com.jkjamies.nasa.apod.domain.models.Apod

/**
 * Local data source for the Astronomy Picture of the Day
 */
internal interface ApodLocalDataSource {
    /**
     * Get the Astronomy Picture of the Day
     *
     * @return the Astronomy Picture of the Day as an [Apod]
     */
    suspend fun getApod(): Apod?

    /**
     * Save the [apod] (Astronomy Picture of the Day) to the local database.
     */
    suspend fun saveApod(apod: Apod)
}
