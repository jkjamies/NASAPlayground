package com.jkjamies.nasa.apod.domain.usecase

import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.repository.ApodRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

class GetApodUseCaseTest : FunSpec({

    val apodRepository: ApodRepository = mockk()
    val getApodUseCase = GetApodUseCase(apodRepository)

    test("invoke() with apod data") {
        val apod =
            Apod(
                copyright = "Copyright",
                date = "2023-08-01",
                explanation = "Explanation",
                hdurl = "hdurl",
                media_type = "image",
                service_version = "v1",
                title = "Title",
                url = "url",
                error = null,
            )
        coEvery { apodRepository.getApod() } returns flow { emit(apod) }

        val result = getApodUseCase().toList()

        result shouldBe listOf(apod)
    }

    test("invoke() with null apod data") {
        coEvery { apodRepository.getApod() } returns flow { emit(null) }

        val result = getApodUseCase().toList()

        result shouldBe listOf(null)
    }
})
