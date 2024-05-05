package com.jkjamies.nasa.neos.domain.usecase

import com.jkjamies.nasa.neos.domain.models.NearEarthObject
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

/**
 * Use case for getting the Near Earth Object
 */
@Single
internal class GetNeoUseCase(
    private val neosRepository: NeosRepository,
) {
    /**
     * Get the Near Earth Object
     *
     * @param neoId the ID of the Near Earth Object
     * @return the Near Earth Object by a given ID as a [Flow] of [NearEarthObject]
     */
    suspend operator fun invoke(neoId: String): Flow<NearEarthObject?> = neosRepository.getNeo(neoId)
}
