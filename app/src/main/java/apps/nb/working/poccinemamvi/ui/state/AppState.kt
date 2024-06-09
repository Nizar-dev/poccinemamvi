package apps.nb.working.poccinemamvi.ui.state

import apps.nb.working.pocmoviesbymvi.ui.state.DiscoverMoviesState
import apps.nb.working.pocmoviesbymvi.ui.state.MovieDetailsState
import apps.nb.working.pocmoviesbymvi.ui.state.ResultState



data class AppState(
    val discoverMoviesState: DiscoverMoviesState = DiscoverMoviesState(),
    val movieDetailsState: MovieDetailsState = MovieDetailsState(),
    val resultState: ResultState = ResultState()
)