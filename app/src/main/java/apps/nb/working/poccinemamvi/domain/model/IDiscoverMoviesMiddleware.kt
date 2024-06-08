package apps.nb.working.poccinemamvi.domain.model

import androidx.paging.PagingData
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffects
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffectsMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.Container


interface IDiscoverMoviesMiddleware {

    fun init(
        container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>,
        viewModelScope: CoroutineScope,
        callback: (Flow<PagingData<MovieDiscoverItem>>) -> Unit
    )

    fun reduce(discoverMiddlewaresMoviesSideEffects: DiscoverMoviesSideEffectsMiddleware)
}