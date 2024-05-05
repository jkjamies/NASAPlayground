package com.jkjamies.nasa.playground.presentation.home

import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.models.ApodError
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
class HomeViewModelTest : FunSpec({

    Dispatchers.setMain(Dispatchers.Unconfined)

    val nasaApiServices: NasaApiServices = mockk()
    val viewModel = HomeViewModel(nasaApiServices)

    val apod =
        Apod(
            copyright = "this is copyrighted",
            date = "2021-08-01",
            explanation = "test explanation",
            hdurl = "testHdUrl",
            media_type = "image",
            service_version = "v1",
            title = "NGC 6914: The Glowing Eye Nebula",
            url = "testUrl",
            error =
                ApodError(
                    code = "testCode",
                    msg = "test message",
                ),
        )

    test("getApod should update state with ApodResponse") {
        // Mock apodApi's getApod() to return success
        coEvery { nasaApiServices.apodApi.getApod() } returns flowOf(apod)

        // Trigger getApod()
        viewModel.getApod()

        // Verify viewmodel's state updated
        viewModel.state.value.apod shouldBe apod
    }
})
