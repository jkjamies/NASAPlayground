package com.jkjamies.nasa.neos.data.repository

import co.touchlab.kermit.Logger
import com.jkjamies.nasa.neos.data.localDataSource.NeosLocalDataSource
import com.jkjamies.nasa.neos.data.remoteDataSource.NeosRemoteDataSource
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single(binds = [NeosRepository::class])
internal class NeosRepositoryImpl(
    private val localDataSource: NeosLocalDataSource,
    private val remoteDataSource: NeosRemoteDataSource,
) : NeosRepository {
    override suspend fun getApod(): Flow<NeosResponse?> =
        flow {
            // emit retained information immediately
            val local = localDataSource.getNeos()
            emit(local)

            // if not today, fetch from remote and save to local, emit remote data
            if (local == null) { // TODO: add check for stale data
                remoteDataSource.getNeos()
                    .onSuccess { neos ->
                        Logger.d("NeosRemoteDataSourceImpl") {
                            """
                            Requested Near Earth Objects of the week:
                            $neos
                            """.trimIndent()
                        }
                        emit(neos)
                        neos?.let { localDataSource.saveNeos(it) }
                    }
                    .onFailure {
                        emit(null)
                    }
            }
        }
}
