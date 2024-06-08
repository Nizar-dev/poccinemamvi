package apps.nb.working.poccinemamvi.data.remote

import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.pocmoviesbymvi.data.source.remote.BasicMoviesResponse
import apps.nb.working.pocmoviesbymvi.data.source.remote.MovieDetailsResponse


fun convertApiResponseToMovies(apiResponse: BasicMoviesResponse<MovieDiscoverItem>): List<MovieDiscoverItem> {
    return apiResponse.results?.map { results ->
        MovieDiscoverItem(
            id = results.id,
            title = results.title,
            overview = results.overview,
            posterPath = "https://image.tmdb.org/t/p/w500${results.posterPath}",
            releaseDate = results.releaseDate,
            voteAverage = results.voteAverage
        )
    } ?: emptyList()
}

fun convertApiResponseToMovieDetail(movieResult: MovieDetailsResponse?): MovieDetails {
    return MovieDetails(
        id = movieResult?.id,
        title = movieResult?.title,
        overview = movieResult?.overview,
        posterPath = "https://image.tmdb.org/t/p/w500${movieResult?.posterPath}",
        releaseDate = movieResult?.releaseDate,
        voteAverage = movieResult?.voteAverage
    )
}