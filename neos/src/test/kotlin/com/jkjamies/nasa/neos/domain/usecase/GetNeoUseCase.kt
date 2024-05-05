package com.jkjamies.nasa.neos.domain.usecase

import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

class GetNeoUseCaseTest : FunSpec({

    test("invoke should return the Near Earth Object from repository") {
        // Given
        val neosRepository: NeosRepository = mockk()
        val nearEarthObject: NearEarthObject = mockk()
        val neoId = "neoId"
        val getNeoUseCase = GetNeoUseCase(neosRepository)
        coEvery { neosRepository.getNeo(neoId) } returns flowOf(nearEarthObject)

        // When
        val result: Flow<NearEarthObject?> = getNeoUseCase(neoId)

        // Then
        result.firstOrNull() shouldBe nearEarthObject
    }
})
