package com.jkjamies.nasa.playground

import android.content.Context
import com.jkjamies.nasa.apod.ApodApi
import com.jkjamies.nasa.neos.NeosApi
import com.jkjamies.nasa.playground.presentation.home.HomeViewModel
import com.jkjamies.nasa.playground.presentation.neoDetail.NeoDetailViewModel
import io.kotest.core.spec.style.FunSpec
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.ksp.generated.module
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
class KoinTest : FunSpec({

    test("koin modules should be valid") {
        // Verify Koin configurations

        // context module contains android contet, apod api, and neos api facades
        contextModule.verify(
            extraTypes =
                listOf(
                    Context::class,
                    ApodApi::class,
                    NeosApi::class,
                ),
        )

        // app module contains nasa api services (holds apod and neos apis as facade pattern)
        AppModule().module.verify(
            // List types used in definitions but not declared directly (like parameters injection)
            extraTypes =
                listOf(
                    NasaApiServices::class,
                    HomeViewModel::class,
                    NeoDetailViewModel::class,
                ),
        )
    }
})
