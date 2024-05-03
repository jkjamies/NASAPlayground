package com.jkjamies.nasa.apod.data.repository

import co.touchlab.kermit.Logger
import com.jkjamies.nasa.apod.data.localDataSource.ApodLocalDataSource
import com.jkjamies.nasa.apod.data.remoteDataSource.ApodRemoteDataSource
import com.jkjamies.nasa.apod.domain.models.Apod
import com.jkjamies.nasa.apod.domain.repository.ApodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Single(binds = [ApodRepository::class])
internal class ApodRepositoryImpl(
    private val localDataSource: ApodLocalDataSource,
    private val remoteDataSource: ApodRemoteDataSource,
) : ApodRepository {
    override suspend fun getApod(): Flow<Apod?> =
        flow {
            // emit retained information immediately
            val local = localDataSource.getApod()
            emit(local)

            // if not today, fetch from remote and save to local, emit remote data
            if (local == null || !isToday(local.date)) {
                remoteDataSource.getApod()
                    .onSuccess { apod ->
                        Logger.d("ApodRemoteDataSourceImpl") {
                            """
                            Requested Astronomy Picture of the Day:
                            $apod
                            """.trimIndent()
                        }
                        emit(apod)
                        apod?.let { localDataSource.saveApod(it) }
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val today = dateFormat.format(Date())
        return date == today
    }
}
