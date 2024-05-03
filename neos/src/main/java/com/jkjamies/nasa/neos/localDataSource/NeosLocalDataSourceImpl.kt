package com.jkjamies.nasa.neos.localDataSource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kermit.Logger
import com.jkjamies.nasa.neos.NeosDatabase
import com.jkjamies.nasa.neos.data.localDataSource.NeosLocalDataSource
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import org.koin.core.annotation.Single

@Single(binds = [NeosLocalDataSource::class])
internal class NeosLocalDataSourceImpl(
    context: Context,
    private val driver: SqlDriver = AndroidSqliteDriver(NeosDatabase.Schema, context, "neos.db"),
) : NeosLocalDataSource {
    private val database = NeosDatabase(driver)

    override suspend fun getNeos(): NeosResponse? {
        // Retrieve the cached data
        val cachedData =
            database.neosDatabaseQueries.getSavedApod().executeAsOneOrNull() ?: return null
        Logger.d("NeosLocalDataSourceImpl") {
            """
            Retrieved cached data: $cachedData
            """.trimIndent()
        }
        // Convert the cached data to the domain model
        return null
//        return NeosResponse()
    }

    override suspend fun saveNeos(neos: NeosResponse) {
        Logger.d("NeosLocalDataSourceImpl") {
            """
            Saving cached data: $neos
            """.trimIndent()
        }

        // Clear the database before saving the new data
        database.neosDatabaseQueries.deleteAllApods()

        // Save the new data
//        database.neosDatabaseQueries.replaceApod()
    }
}
