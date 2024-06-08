package apps.nb.working.poccinemamvi.domain.middleware

import androidx.paging.PagingData
import apps.nb.working.poccinemamvi.domain.model.IDiscoverMoviesMiddleware
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffects
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffectsMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.Container


class InitDiscoverFeatureMiddleware constructor(
    private val middlewareList: ArrayList<IDiscoverMoviesMiddleware>
) {

    fun init(
        container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>,
        viewModelScope: CoroutineScope,
        callback: (Flow<PagingData<MovieDiscoverItem>>) -> Unit
    ) {
        middlewareList.forEach {
            it.init(container, viewModelScope, callback)
        }
    }

    fun reduce(discoverMiddlewaresMoviesSideEffects: DiscoverMoviesSideEffectsMiddleware) {
        middlewareList.forEach {
            it.reduce(discoverMiddlewaresMoviesSideEffects)
        }
    }
}