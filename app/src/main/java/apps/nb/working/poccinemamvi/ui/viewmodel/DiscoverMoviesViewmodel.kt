package apps.nb.working.poccinemamvi.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import apps.nb.working.poccinemamvi.domain.middleware.InitDiscoverFeatureMiddleware
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.BaseViewModel
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffects
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffectsMiddleware


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import javax.inject.Inject


@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
    private val initDiscoverFeatureMiddleware: InitDiscoverFeatureMiddleware
) : BaseViewModel<DiscoverMoviesSideEffects>(appState) {

    lateinit var getDiscoverMovies: Flow<PagingData<MovieDiscoverItem>>

    init {
        initDiscoverFeatureMiddleware.init(container, viewModelScope) {
            getDiscoverMovies = it
        }
    }

    fun getCurrentPageAndScrollOffset() = intent {
        initDiscoverFeatureMiddleware.reduce(DiscoverMoviesSideEffectsMiddleware.GetCurrentDiscoverPageAndScrollOffset)
    }

    fun setLastScrolledPage(index: Int) = intent {
        initDiscoverFeatureMiddleware.reduce(
            DiscoverMoviesSideEffectsMiddleware.SetLastScrolledPage(
                index
            )
        )
    }

    fun triggerOnPageChanged(index: Int) = intent {
        initDiscoverFeatureMiddleware.reduce(
            DiscoverMoviesSideEffectsMiddleware.TriggerOnPageChanged(
                index
            )
        )
    }

    fun navigateToMovieDetails(movieId: Int) = intent {
        initDiscoverFeatureMiddleware.reduce(
            DiscoverMoviesSideEffectsMiddleware.NavigateToMovie(
                movieId
            )
        )
    }

    /**
     * If any of the load states are in error, post a side effect to try reloading the page
     *
     * @param loadStates LoadStates - This is the LoadStates object that is passed to the
     * PagingDataAdapter.
     */
    fun handleLoadState(loadStates: LoadStates) = intent {
        val errorLoadState = arrayOf(
            loadStates.append,
            loadStates.prepend,
            loadStates.refresh
        ).filterIsInstance(LoadState.Error::class.java).firstOrNull()
        val loadState = arrayOf(
            loadStates.append,
            loadStates.prepend,
            loadStates.refresh
        ).filterIsInstance(LoadState.Loading::class.java).firstOrNull()

        val throwable = errorLoadState?.error
        if (throwable != null || loadState == null) {
            postSideEffect(DiscoverMoviesSideEffects.TryReloadDiscoverPage)
        }
    }
}