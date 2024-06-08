package apps.nb.working.poccinemamvi.ui.state

import apps.nb.working.pocmoviesbymvi.ui.state.HomeState
import apps.nb.working.pocmoviesbymvi.ui.state.MovieDetailsState
import apps.nb.working.pocmoviesbymvi.ui.state.SearchState


data class AppState(
    val homeState: HomeState = HomeState(),
    val movieDetailsState: List<MovieDetailsState> = listOf(),
    val searchState: SearchState = SearchState(),
)