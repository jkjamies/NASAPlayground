package com.jkjamies.nasa.neos.domain.usecase

import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single

/**
 * Use case for getting the Near Earth Objects of the week
 */
@Single
internal class GetNeosUseCase(
    private val neosRepository: NeosRepository,
) {
    /**
     * Get the Near Earth Objects of the week
     *
     * @return the Near Earth Objects of the week as a [Flow] of [NeosResponse]
     */
    suspend operator fun invoke(): Flow<NeosResponse?> = neosRepository.getNeos()
}
