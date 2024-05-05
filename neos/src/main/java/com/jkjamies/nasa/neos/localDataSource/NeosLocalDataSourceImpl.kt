package com.jkjamies.nasa.neos.localDataSource

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import co.touchlab.kermit.Logger
import com.jkjamies.nasa.neos.NeosDatabase
import com.jkjamies.nasa.neos.data.localDataSource.NeosLocalDataSource
import com.jkjamies.nasa.neos.domain.models.FeedLinks
import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
            database.neosDatabaseQueries.getSavedNeos().executeAsOneOrNull() ?: return null
        Logger.d("NeosLocalDataSourceImpl") {
            """
            Retrieved cached data: $cachedData
            """.trimIndent()
        }
        // Convert the cached data to the domain model
        return NeosResponse(
            date = cachedData.date,
            links =
                FeedLinks(
                    next = cachedData.links_next,
                    prev = cachedData.links_prev,
                    self = cachedData.links_self,
                ),
            element_count = cachedData.element_count?.toInt(),
            near_earth_objects =
                cachedData.near_earth_objects_json?.let { json ->
                    Json.decodeFromString<Map<String, List<NearEarthObject>>?>(json)
                },
        )
    }

    override suspend fun saveNeos(
        neos: NeosResponse,
        cachingDate: String,
    ) {
        Logger.d("NeosLocalDataSourceImpl") {
            """
            Saving cached data: $neos
            """.trimIndent()
        }

        // Clear the database before saving the new data
        database.neosDatabaseQueries.deleteNeos()

        // Save the new data
        database.neosDatabaseQueries.saveNeos(
            date = cachingDate,
            elementCount = neos.element_count?.toLong(),
            linksNext = neos.links?.next,
            linksPrev = neos.links?.prev,
            linksSelf = neos.links?.self,
            nearEarthObjectsJson = Json.encodeToString(neos.near_earth_objects),
        )
    }

    override suspend fun getNeo(neoId: String): NearEarthObject? {
        // Keeping the database simple, we can just fetch the entire cached response
        // using our defined getNeos() method and then filter the NEOs by ID
        val neoResponse = getNeos() ?: return null
        // Get the list of NEOs from the cached data's raw json saved payload
        val neo =
            neoResponse.near_earth_objects?.values?.flatten()?.find {
                it.id == neoId
            } ?: return null
        Logger.d("NeosLocalDataSourceImpl") {
            """
            Retrieved NEO cached data: $neo
            """.trimIndent()
        }
        return neo
    }
}
