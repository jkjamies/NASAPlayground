package com.jkjamies.nasa.apod

import android.content.Context
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.usecase.GetApodUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.defaultModule

/**
 * API Facade for the Astronomy Picture of the Day
 */
class ApodApi(context: Context) {
    // opting to declare no koin module, takes all from apod module

    /** Koin application for the Astronomy Picture of the Day */
    private val koinApp =
        koinApplication {
            logger(KermitKoinLogger(Logger.withTag("Koin APoD")))
            androidContext(context)
            defaultModule()
        }

    /** Use case for getting the Astronomy Picture of the Day */
    private val getApodUseCase = koinApp.koin.get<GetApodUseCase>()

    /**
     * Get the Astronomy Picture of the Day
     *
     * @return the Astronomy Picture of the Day as a [Flow] of [Apod]
     */
    suspend fun getApod(): Flow<Apod?> = getApodUseCase()
}
