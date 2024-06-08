package apps.nb.working.pocmoviesbymvi.ui.state

import apps.nb.working.poccinemamvi.domain.model.MovieDetails


data class MovieDetailsState(
    override var id: Int = -1,
    override var details: MovieDetails? = null,
    override var state: DetailsState = DetailsState.DEFAULT,
    override var isAdded: Boolean = true,
    override var loadingState: LoadingState = LoadingState.LOADING
): BaseDetails<MovieDetails>()