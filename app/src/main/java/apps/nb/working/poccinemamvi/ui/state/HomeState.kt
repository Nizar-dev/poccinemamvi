package apps.nb.working.pocmoviesbymvi.ui.state


data class HomeState(
    val isOpen: Boolean = false,
    val discoverMoviesState: DiscoverMoviesState = DiscoverMoviesState()
)