package com.jkjamies.nasa.apod.data.repository

import com.jkjamies.nasa.apod.data.localDataSource.ApodLocalDataSource
import com.jkjamies.nasa.apod.data.remoteDataSource.ApodRemoteDataSource
import com.jkjamies.nasa.apod.domain.models.Apod
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@ExperimentalCoroutinesApi
class ApodRepositoryImplTest : FunSpec({

    val localDataSource: ApodLocalDataSource = mockk()
    val remoteDataSource: ApodRemoteDataSource = mockk()
    val repository = ApodRepositoryImpl(localDataSource, remoteDataSource)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val today = dateFormat.format(Date())

    test("getApod() emits local data when available") {
        val localApod =
            Apod(
                copyright = "Copyright",
                date = today,
                explanation = "Local Explanation",
                hdurl = "hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Local Title",
                url = "local_url",
                error = null,
            )
        coEvery { localDataSource.getApod() } returns localApod
        coEvery { remoteDataSource.getApod() } returns Result.success(null)

        val result = repository.getApod().toList()

        result shouldBe listOf(localApod)
    }

    test("getApod() fetches remote data when local data is not available") {
        val remoteApod =
            Apod(
                copyright = "Copyright",
                date = today,
                explanation = "Remote Explanation",
                hdurl = "hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Remote Title",
                url = "remote_url",
                error = null,
            )
        coEvery { localDataSource.getApod() } returns null
        coEvery { localDataSource.saveApod(any()) } just runs
        coEvery { remoteDataSource.getApod() } returns Result.success(remoteApod)

        val result = repository.getApod().toList()

        result shouldBe listOf(null, remoteApod)
    }

    test("getApod() fetches remote data when local data date is not today") {
        val localApod =
            Apod(
                copyright = "Copyright",
                date = "2024-04-29",
                explanation = "Local Explanation",
                hdurl = "hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Local Title",
                url = "local_url",
                error = null,
            )
        val remoteApod =
            Apod(
                copyright = "Copyright",
                date = today,
                explanation = "Remote Explanation",
                hdurl = "hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Remote Title",
                url = "remote_url",
                error = null,
            )
        coEvery { localDataSource.getApod() } returns localApod
        coEvery { remoteDataSource.getApod() } returns Result.success(remoteApod)

        val result = repository.getApod().toList()

        result shouldBe listOf(localApod, remoteApod)
    }

    test("getApod() emits null when both local and remote data fetch fails") {
        coEvery { localDataSource.getApod() } returns null
        coEvery { remoteDataSource.getApod() } returns Result.failure(Exception())

        val result = repository.getApod().toList()

        result shouldBe listOf(null, null)
    }
})
