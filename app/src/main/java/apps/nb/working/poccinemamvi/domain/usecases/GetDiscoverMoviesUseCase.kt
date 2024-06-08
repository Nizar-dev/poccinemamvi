package apps.nb.working.poccinemamvi.domain.usecases

import androidx.paging.PagingData
import apps.nb.working.poccinemamvi.data.repository.MoviesRemoteRepo
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject
sealed class GetDiscoverMoviesResult {
    data class Success(val movies: Flow<PagingData<MovieDiscoverItem>>) : GetDiscoverMoviesResult()
    data class Error(val message: String) : GetDiscoverMoviesResult()
}
class GetDiscoverMoviesUseCase @Inject constructor(
    private val moviesRemoteRepo: MoviesRemoteRepo
) {
    suspend fun getMovies(): Flow<PagingData<MovieDiscoverItem>> {
        return moviesRemoteRepo.getDiscoverMovies()
    }
}