package com.jkjamies.nasa.neos

import android.content.Context
import co.touchlab.kermit.Logger
import co.touchlab.kermit.koin.KermitKoinLogger
import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.usecase.GetNeoUseCase
import com.jkjamies.nasa.neos.domain.usecase.GetNeosUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module

/**
 * API Facade for the Near Earth Objects of the week
 */
class NeosApi(context: Context) {
    // opting to declare no koin module, takes all from neos module

    /** Koin application for the Near Earth Objects of the week */
    private val koinApp =
        koinApplication {
            logger(KermitKoinLogger(Logger.withTag("Koin NEOs")))
            androidContext(context)
            modules(NeosModule().module)
        }

    /** Use case for getting the Near Earth Objects of the week */
    private val getNeosUseCase = koinApp.koin.get<GetNeosUseCase>()

    /** Use case for getting the Near Earth Object by its ID */
    private val getNeoUseCase = koinApp.koin.get<GetNeoUseCase>()

    /**
     * Get the Near Earth Objects of the week
     *
     * @return the Near Earth Objects of the week as a [Flow] of [NeosResponse]
     */
    suspend fun getNeos(): Flow<NeosResponse?> = getNeosUseCase()

    /**
     * Get the Near Earth Object by its ID
     *
     * @param neoId the ID of the Near Earth Object
     * @return the Near Earth Object by its ID as a [Flow] of [NearEarthObject]
     */
    suspend fun getNeoById(neoId: String): Flow<NearEarthObject?> = getNeoUseCase(neoId)
}

@Module
@ComponentScan
internal class NeosModule
