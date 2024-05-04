package com.jkjamies.nasa.neos.remoteDataSource

import com.jkjamies.nasa.neos.shared.neosResponse
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.runBlocking

class NeosRemoteDataSourceImplTest : FunSpec({

    test("getNeos returns the result when the server returns a successful response") {
        // Given
        val mockResponse = """
    {
        "date": "2024-05-03",
        "links": {
            "next": "https://example.com/next",
            "prev": "https://example.com/prev",
            "self": "https://example.com/self"
        },
        "element_count": 10,
        "near_earth_objects": {
            "2024-05-03": [
                {
                    "links": {
                        "self": "https://example.com/neo/self"
                    },
                    "id": "1",
                    "neo_reference_id": "123456",
                    "name": "Example NEO",
                    "nasa_jpl_url": "https://example.com/neo",
                    "absolute_magnitude_h": 22.5,
                    "estimated_diameter": {
                        "kilometers": {
                            "estimated_diameter_min": 100.0,
                            "estimated_diameter_max": 200.0
                        },
                        "meters": {
                            "estimated_diameter_min": 100000.0,
                            "estimated_diameter_max": 200000.0
                        },
                        "miles": {
                            "estimated_diameter_min": 62.137,
                            "estimated_diameter_max": 124.274
                        },
                        "feet": {
                            "estimated_diameter_min": 328084.0,
                            "estimated_diameter_max": 656168.0
                        }
                    },
                    "is_potentially_hazardous_asteroid": true,
                    "close_approach_data": [
                        {
                            "close_approach_date": "2024-05-03",
                            "close_approach_date_full": "2024-May-03 14:21",
                            "epoch_date_close_approach": 1738276860000,
                            "relative_velocity": {
                                "kilometers_per_second": "25.6",
                                "kilometers_per_hour": "92160",
                                "miles_per_hour": "57231"
                            },
                            "miss_distance": {
                                "astronomical": "0.025 AU",
                                "lunar": "9.8 LD",
                                "kilometers": "3,734,489",
                                "miles": "2,319,425"
                            },
                            "orbiting_body": "Earth"
                        }
                    ],
                    "is_sentry_object": false
                }
            ]
        }
    }
"""

        val mockEngine =
            MockEngine { request ->
                respond(
                    content = ByteReadChannel(mockResponse),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            }

        val httpClient =
            HttpClient(mockEngine) {
                install(ContentNegotiation) {
                    json()
                }
            }

        val neosRemoteDataSource = NeosRemoteDataSourceImpl(httpClient)

        val expectedResult = neosResponse

        // When
        val result = runBlocking { neosRemoteDataSource.getNeos() }

        // Then
        result.isSuccess shouldBe true
        result.getOrNull() shouldBe expectedResult
    }

    test("getNeos returns an error when the server returns an error response") {
        // Given
        val mockEngineWithError =
            MockEngine { request ->
                respond(
                    content = ByteReadChannel("{}"),
                    status = HttpStatusCode.InternalServerError,
                    headers =
                        headersOf(
                            HttpHeaders.ContentType,
                            ContentType.Application.Json.toString(),
                        ),
                )
            }

        val httpClientWithError =
            HttpClient(mockEngineWithError) {
                install(ContentNegotiation) {
                    json()
                }
            }

        val neosRemoteDataSourceWithError = NeosRemoteDataSourceImpl(httpClientWithError)

        // When
        val result = runBlocking { neosRemoteDataSourceWithError.getNeos() }

        // Then
        result.isFailure shouldBe true
    }
})
