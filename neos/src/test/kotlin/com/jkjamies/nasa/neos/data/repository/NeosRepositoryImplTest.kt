package com.jkjamies.nasa.neos.data.repository

import com.jkjamies.nasa.neos.data.localDataSource.NeosLocalDataSource
import com.jkjamies.nasa.neos.data.remoteDataSource.NeosRemoteDataSource
import com.jkjamies.nasa.neos.shared.neosResponse
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
class NeosRepositoryImplTest : FunSpec({

    val localDataSource: NeosLocalDataSource = mockk()
    val remoteDataSource: NeosRemoteDataSource = mockk()
    val neosRepository = NeosRepositoryImpl(localDataSource, remoteDataSource)

    test("getNeos when local data available uses local") {
        // Given
        val localNeosResponse = neosResponse
        coEvery { localDataSource.getNeos() } returns localNeosResponse

        // When
        val flow = neosRepository.getNeos()

        // Then
        runBlocking {
            val response = flow.first()
            response shouldBe localNeosResponse
        }
    }

    test("getNeos when local data not available calls remote") {
        // Given
        coEvery { localDataSource.getNeos() } returns null
        coEvery { remoteDataSource.getNeos() } returns Result.success(neosResponse)
        coEvery { localDataSource.saveNeos(neosResponse, any()) } just runs

        // When
        val flow = neosRepository.getNeos()

        // Then
        runBlocking {
            val response = flow.toList()
            response shouldBe listOf(null, neosResponse)
            coVerify { localDataSource.saveNeos(neosResponse, any()) }
        }
    }

    test("getNeos when local data not today calls remote") {
        // Given
        coEvery { localDataSource.getNeos() } returns neosResponse
        coEvery { remoteDataSource.getNeos() } returns Result.success(neosResponse)

        // When
        val flow = neosRepository.getNeos()

        // Then
        runBlocking {
            val response = flow.first()
            response shouldBe neosResponse
            coVerify { localDataSource.saveNeos(neosResponse, any()) }
        }
    }
})
