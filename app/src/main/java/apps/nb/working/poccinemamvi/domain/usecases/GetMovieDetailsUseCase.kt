package apps.nb.working.poccinemamvi.domain.usecases

import apps.nb.working.poccinemamvi.data.repository.MoviesRemoteRepoImpl
import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import javax.inject.Inject


class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MoviesRemoteRepoImpl
) {
    suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return movieRepository.getMovieDetails(movieId)
    }
}