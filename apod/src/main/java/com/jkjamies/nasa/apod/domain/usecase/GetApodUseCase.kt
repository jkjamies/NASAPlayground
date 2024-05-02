package com.jkjamies.nasa.apod.domain.usecase

import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

/**
 * Use case for getting the Astronomy Picture of the Day
 */
@Single
internal class GetApodUseCase(
    private val apodRepository: ApodRepository,
) {
    /**
     * Get the Astronomy Picture of the Day
     *
     * @return the Astronomy Picture of the Day as a [Flow] of [Apod]
     */
    suspend operator fun invoke(): Flow<Apod?> = apodRepository.getApod()
}
