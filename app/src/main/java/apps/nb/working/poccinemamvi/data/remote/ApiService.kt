package apps.nb.working.poccinemamvi.data.remote


import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.pocmoviesbymvi.data.source.remote.BasicMoviesResponse
import apps.nb.working.pocmoviesbymvi.data.source.remote.MovieDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // Découvrir les films populaires
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String? = "popularity.desc",
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("include_video") includeVideo: Boolean = true,
    ): Response<BasicMoviesResponse<MovieDetailsResponse>>

    @GET("search/movie")
    suspend fun getMoviesByTitle(
        @Query("query") query: String = "The Dark Knight",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US"
    ): Response<BasicMoviesResponse<MovieDiscoverItem>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int
    ): Response<MovieDetailsResponse>


}
