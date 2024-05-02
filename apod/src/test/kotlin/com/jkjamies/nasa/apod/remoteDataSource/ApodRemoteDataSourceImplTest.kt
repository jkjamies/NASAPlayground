package com.jkjamies.nasa.apod.remoteDataSource

import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.models.ApodError
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class ApodRemoteDataSourceImplFunSpec : FunSpec({

    test("getApod returns the result when the server returns a successful response") {
        val mockResponse = """
            {
                "copyright": "copyright",
                "date": "2023-08-01",
                "explanation": "Explanation",
                "hdurl": "Hdurl",
                "media_type": "image",
                "service_version": "v1",
                "title": "Title",
                "url": "Url",
                "error": null
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

        val apodRemoteDataSourceImpl = ApodRemoteDataSourceImpl(httpClient)

        val result =
            Apod(
                copyright = "copyright",
                date = "2023-08-01",
                explanation = "Explanation",
                hdurl = "Hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Title",
                url = "Url",
                error = null,
            )

        runTest {
            apodRemoteDataSourceImpl.getApod() shouldBe Result.success(result)
        }
    }

    test("getApod returns an error when the endpoint returns an error response") {
        val mockResponse = """
            {
                "copyright": null,
                "date": null,
                "explanation": null,
                "hdurl": null,
                "media_type": null,
                "service_version": null,
                "title": null,
                "url": null,
                "error": {
                    "code": "error_code",
                    "msg": "error_message"
                }
            }
        """

        val mockEngineWithError =
            MockEngine { request ->
                respond(
                    content = ByteReadChannel(mockResponse),
                    status = HttpStatusCode.InternalServerError,
                    headers = headersOf(HttpHeaders.ContentType, "application/json"),
                )
            }

        val httpClientWithError =
            HttpClient(mockEngineWithError) {
                install(ContentNegotiation) {
                    json()
                }
            }

        val apodRemoteDataSourceImplWithError = ApodRemoteDataSourceImpl(httpClientWithError)

        val result =
            Apod(
                copyright = null,
                date = null,
                explanation = null,
                hdurl = null,
                media_type = null,
                service_version = null,
                title = null,
                url = null,
                error =
                    ApodError(
                        code = "error_code",
                        msg = "error_message",
                    ),
            )

        runTest {
            apodRemoteDataSourceImplWithError.getApod() shouldBe Result.success(result)
        }
    }
})
