package com.jkjamies.nasa.neos.remoteDataSource

import com.jkjamies.nasa.core.httpClient
import com.jkjamies.nasa.neos.data.remoteDataSource.NeosRemoteDataSource
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Single(binds = [NeosRemoteDataSource::class])
internal class NeosRemoteDataSourceImpl(
    private val httpClient: HttpClient = httpClient { },
) : NeosRemoteDataSource {
    override suspend fun getNeos(): Result<NeosResponse?> {
        runCatching {
            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val currentDate = Calendar.getInstance()
            val date7DaysLater = Calendar.getInstance()
            date7DaysLater.add(Calendar.DAY_OF_MONTH, 7)
            val currentDateString = dateFormatter.format(currentDate.time)
            val date7DaysLaterString = dateFormatter.format(date7DaysLater.time)

            // Fetch the Near Earth Objects of the week from the NASA API
            // The API key is a demo key and is rate limited
            // You should move this to build config or a secure location in a real app
            val request =
                httpClient.get(
                    "https://api.nasa.gov/neo/rest/v1/feed?start_date=$currentDateString&end_date=$date7DaysLaterString&api_key=DEMO_KEY",
                )
            // Return the Near Earth Objects for the week
            return Result.success(request.body<NeosResponse>())
        }.onFailure {
            // Return the error if there was one
            return Result.failure(it)
        }
        // This should never be reached
        return Result.failure(Exception("Unknown Error"))
    }
}
