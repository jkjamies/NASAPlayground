package com.jkjamies.nasa.apod.localDataSource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kermit.Logger
import com.jkjamies.nasa.apod.ApodDatabase
import com.jkjamies.nasa.apod.data.localDataSource.ApodLocalDataSource
import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.models.ApodError
import org.koin.core.annotation.Single

@Single(binds = [ApodLocalDataSource::class])
internal class ApodLocalDataSourceImpl(
    context: Context,
) : ApodLocalDataSource {
    private val driver: SqlDriver = AndroidSqliteDriver(ApodDatabase.Schema, context, "apod.db")
    private val database = ApodDatabase(driver)

    override suspend fun getApod(): Apod? {
        // Retrieve the cached data
        val cachedData =
            database.apodDatabaseQueries.getSavedApod().executeAsOneOrNull() ?: return null
        Logger.d("ApodLocalDataSourceImpl") {
            """
            Retrieved cached data: $cachedData
            """.trimIndent()
        }
        // Convert the cached data to the domain model
        return Apod(
            copyright = cachedData.copyright,
            date = cachedData.date,
            explanation = cachedData.explanation,
            hdurl = cachedData.hdurl,
            media_type = cachedData.media_type,
            service_version = cachedData.service_version,
            title = cachedData.title,
            url = cachedData.url,
            error = ApodError(cachedData.error_code, cachedData.error_msg),
        )
    }

    override suspend fun saveApod(apod: Apod) {
        Logger.d("ApodLocalDataSourceImpl") {
            """
            Saving cached data: $apod
            """.trimIndent()
        }

        // Clear the database before saving the new data
        database.apodDatabaseQueries.deleteAllApods()

        // Save the new data
        database.apodDatabaseQueries.replaceApod(
            copyright = apod.copyright,
            date = apod.date,
            explanation = apod.explanation,
            hdurl = apod.hdurl,
            media_type = apod.media_type,
            service_version = apod.service_version,
            title = apod.title,
            url = apod.url,
            error_code = apod.error?.code,
            error_msg = apod.error?.msg,
        )
    }
}
