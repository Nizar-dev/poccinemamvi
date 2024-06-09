package apps.nb.working.poccinemamvi.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import apps.nb.working.poccinemamvi.data.remote.ApiService
import apps.nb.working.poccinemamvi.data.remote.MoviesPagingSource
import apps.nb.working.poccinemamvi.data.remote.SearchMoviesPagingSource
import apps.nb.working.poccinemamvi.data.remote.convertApiResponseToMovieDetail
import apps.nb.working.poccinemamvi.data.remote.convertApiResponseToMovies
import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.pocmoviesbymvi.data.source.remote.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class MoviesRemoteRepoImpl @Inject constructor(
    private val apiService: ApiService
) : MoviesRemoteRepo {

    override suspend fun getDiscoverMovies(): Flow<PagingData<MovieDiscoverItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(apiService) }
        ).flow
    }


    override suspend fun getMovieDetails(movieId: Int): MovieDetails{
        val response : Response<MovieDetailsResponse> = apiService.getMovieDetails(movieId)
        return convertApiResponseToMovieDetail(response.body())
    }

    override suspend fun searchMovies(query: String): Flow<PagingData<MovieDiscoverItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchMoviesPagingSource(apiService, query) }
        ).flow
    }
}
