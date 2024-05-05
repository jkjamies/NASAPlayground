package com.jkjamies.nasa.playground.presentation.neoDetail

import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.playground.NasaApiServices
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class NeoDetailViewModelTest : FunSpec({

    Dispatchers.setMain(Dispatchers.Unconfined)

    val mockNeo = mockk<NearEarthObject>()

    test("getNeoById should update state with Neo") {
        // Mock NasaApiServices
        val nasaApiServices = mockk<NasaApiServices>()

        // Mock getNeoById() to return a NeoResponse
        coEvery { nasaApiServices.neosApi.getNeoById(any()) } returns flowOf(mockNeo)

        // Create NeoDetailViewModel
        val viewModel = NeoDetailViewModel(nasaApiServices)

        // Call getNeoById()
        viewModel.getNeoById("123")

        // Verify state updated
        viewModel.state.value.neo shouldBe mockNeo
    }
})
