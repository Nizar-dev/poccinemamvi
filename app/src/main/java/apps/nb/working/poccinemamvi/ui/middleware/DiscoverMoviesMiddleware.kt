package apps.nb.working.poccinemamvi.ui.middleware

import androidx.paging.PagingData
import androidx.paging.cachedIn
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.poccinemamvi.domain.usecases.GetDiscoverMoviesUseCase
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.poccinemamvi.ui.viewmodel.DiscoverMoviesSideEffects
import apps.nb.working.pocmoviesbymvi.ui.state.DiscoverMoviesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import javax.inject.Inject

class DiscoverMoviesMiddleware @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) {
    private lateinit var container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>
    private lateinit var viewModelScope: CoroutineScope
    private lateinit var getDiscoverMovies: Flow<PagingData<MovieDiscoverItem>>


    fun init(
        container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>,
        viewModelScope: CoroutineScope,
        callback: (Flow<PagingData<MovieDiscoverItem>>) -> Unit
    ) {
        this.container = container
        this.viewModelScope = viewModelScope

        viewModelScope.launch {
            getDiscoverMovies = getDiscoverMoviesUseCase.getMovies()
                .cachedIn(viewModelScope)

            callback(getDiscoverMovies)
        }
    }

    fun reduce(discoverMoviesSideEffects: DiscoverMoviesSideEffects, state: DiscoverMoviesState): DiscoverMoviesState {
        return when (discoverMoviesSideEffects) {

            is DiscoverMoviesSideEffects.NavigateToMovie -> {
                // naviguer vers les détails du film
                // et mettre à jour le state en conséquence
                // Exemple : state.copy(navigationEvent = NavigationEvent.NavigateToMovie(discoverMoviesSideEffects.movieId))
                state
            }
            is DiscoverMoviesSideEffects.TryReloadDiscoverPage -> {
                // Réduire l'effet de côté pour recharger la page de découverte
                // et mettre à jour le state en conséquence
                // Exemple : state.copy(isLoading = true)
                state
            }
            is DiscoverMoviesSideEffects.TriggerOnPageChanged -> {
                // Réduire l'effet de côté pour déclencher le changement de page
                // et mettre à jour le state en conséquence
                // Exemple : state.copy(isLoading = true)
                state
            }

            is DiscoverMoviesSideEffects.GetCurrentDiscoverPageAndScrollOffset -> TODO()
        }
    }


}
