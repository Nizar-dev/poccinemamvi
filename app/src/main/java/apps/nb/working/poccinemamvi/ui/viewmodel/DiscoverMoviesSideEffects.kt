package apps.nb.working.poccinemamvi.ui.viewmodel

sealed class DiscoverMoviesSideEffects {
    data class TriggerOnPageChanged(val index: Int) : DiscoverMoviesSideEffects()
    data class GetCurrentDiscoverPageAndScrollOffset(
        val currentPageAndOffset: Int
    ) : DiscoverMoviesSideEffects()
    object TryReloadDiscoverPage : DiscoverMoviesSideEffects()
    object NavigateToMovie : DiscoverMoviesSideEffects()
}