package apps.nb.working.poccinemamvi.data.remote

import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.pocmoviesbymvi.data.source.remote.BasicMoviesResponse
import apps.nb.working.pocmoviesbymvi.data.source.remote.MovieDetailsResponse


fun convertApiResponseToMovies(apiResponse: BasicMoviesResponse<MovieDetailsResponse>): List<MovieDiscoverItem> {
    return apiResponse.results?.map { result ->
        MovieDiscoverItem(
            adult = result.adult,
            backdropPath = result.backdropPath,
            id = result.id,
            originalLanguage = result.originalLanguage,
            originalTitle = result.originalTitle,
            overview = result.overview,
            popularity = result.popularity,
            posterPath = "https://image.tmdb.org/t/p/w500${result.posterPath}",
            releaseDate = result.releaseDate,
            title = result.title,
            video = result.video,
            voteAverage = result.voteAverage,
            voteCount = result.voteCount
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