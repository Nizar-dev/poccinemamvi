package apps.nb.working.poccinemamvi.data.repository

import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteRepo {
    suspend fun getDiscoverMovies(): Flow<PagingData<MovieDiscoverItem>>

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    suspend fun searchMovies(
        query: String,
    ): Flow<PagingData<MovieDiscoverItem>>
}


