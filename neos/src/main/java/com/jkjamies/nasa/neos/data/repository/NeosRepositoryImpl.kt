package com.jkjamies.nasa.neos.data.repository

import co.touchlab.kermit.Logger
import com.jkjamies.nasa.neos.data.localDataSource.NeosLocalDataSource
import com.jkjamies.nasa.neos.data.remoteDataSource.NeosRemoteDataSource
import com.jkjamies.nasa.neos.domain.models.NeosResponse
import com.jkjamies.nasa.neos.domain.repository.NeosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Single(binds = [NeosRepository::class])
internal class NeosRepositoryImpl(
    private val localDataSource: NeosLocalDataSource,
    private val remoteDataSource: NeosRemoteDataSource,
) : NeosRepository {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    override suspend fun getNeos(): Flow<NeosResponse?> =
        flow {
            // emit retained information immediately
            val local = localDataSource.getNeos()
            emit(local)

            // if not today, fetch from remote and save to local, emit remote data
            // more strategies can be used here, this ensures always a week from today of data
            if (local == null || !isToday(local.date)) {
                remoteDataSource.getNeos()
                    .onSuccess { neos ->
                        Logger.d("NeosRemoteDataSourceImpl") {
                            """
                            Requested Near Earth Objects of the week:
                            $neos
                            """.trimIndent()
                        }
                        emit(neos)
                        neos?.let { localDataSource.saveNeos(it, getToday()) }
                    }
                    .onFailure {
                        emit(null)
                    }
            }
        }

    /**
     * Check if the date is today
     */
    private fun isToday(date: String?): Boolean {
        return date == dateFormat.format(Date())
    }

    /**
     * Get the current date
     *
     * @return the current date as a string
     */
    private fun getToday(): String {
        return dateFormat.format(Date())
    }
}
