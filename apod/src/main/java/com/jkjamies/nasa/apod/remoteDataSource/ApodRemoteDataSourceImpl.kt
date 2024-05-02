package com.jkjamies.nasa.apod.remoteDataSource

import com.jkjamies.nasa.apod.data.remoteDataSource.ApodRemoteDataSource
import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.core.httpClient
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single(binds = [ApodRemoteDataSource::class])
internal class ApodRemoteDataSourceImpl(
    private val httpClient: HttpClient = httpClient { },
) : ApodRemoteDataSource {
    override suspend fun getApod(): Result<Apod?> {
        runCatching {
            // Fetch the Astronomy Picture of the Day from the NASA API
            // The API key is a demo key and is rate limited
            // You should move this to build config or a secure location in a real app
            val request = httpClient.get("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY")
            // Return the Astronomy Picture of the Day
            return Result.success(request.body<Apod>())
        }.onFailure {
            // Return the error if there was one
            return Result.failure(it)
        }
        // This should never be reached
        return Result.failure(Exception("Unknown Error"))
    }
}
