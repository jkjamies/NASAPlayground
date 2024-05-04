package com.jkjamies.nasa.neos.domain.usecase

import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

class GetNeosUseCaseTest : FunSpec({

    test("invoke should return the Near Earth Objects of the week from repository") {
        // Given
        val neosRepository: NeosRepository = mockk()
        val neosResponse: NeosResponse? = mockk()
        val getNeosUseCase = GetNeosUseCase(neosRepository)
        coEvery { neosRepository.getNeos() } returns flowOf(neosResponse)

        // When
        val result: Flow<NeosResponse?> = getNeosUseCase()

        // Then
        result.firstOrNull() shouldBe neosResponse
    }
})
