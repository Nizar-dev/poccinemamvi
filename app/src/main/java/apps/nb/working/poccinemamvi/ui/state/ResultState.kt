package apps.nb.working.pocmoviesbymvi.ui.state


data class ResultState(
    val query: String = "",
    val isLoading: Boolean = false,
    val currentPageIndex: Int = -1,
    val scrollOffset: Int = 0,
    val lastSavedPage: Int = 0,
)