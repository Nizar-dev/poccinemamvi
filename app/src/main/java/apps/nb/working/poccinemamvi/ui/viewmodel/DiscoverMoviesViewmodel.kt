package apps.nb.working.poccinemamvi.ui.viewmodel

import androidx.lifecycle.viewModelScope
import apps.nb.working.poccinemamvi.ui.middleware.DiscoverMoviesMiddleware
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.pocmoviesbymvi.ui.state.DiscoverMoviesState
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    appState: MutableStateFlow<AppState>,
    private val discoverMoviesMiddleware: DiscoverMoviesMiddleware
) : BaseViewModel<DiscoverMoviesSideEffects>(appState) {

    // Déclarer le state pour les films découverts
     val discoverMoviesState = MutableStateFlow(DiscoverMoviesState())
    init {
        // Initialiser le middleware avec le container et le scope du viewModel
        discoverMoviesMiddleware.init(container, viewModelScope) { moviesFlow ->
            // Lorsque le flux de films est émis par le middleware, mettre à jour le state
            viewModelScope.launch {
                moviesFlow.collect { pagingData ->
                    // Convertir PagingData en Flow<PagingData>
                    val flowOfPagingData = flowOf(pagingData)
                    // Mettre à jour le state avec les nouvelles données de films
                    discoverMoviesState.value = discoverMoviesState.value.copy(movies = flowOfPagingData)
                }
            }
        }
    }

//
//    fun getCurrentPageAndScrollOffset() = intent {
//        discoverMoviesMiddleware.reduce()
//    }
//
//    fun setLastScrolledPage(index: Int) = intent {
//        discoverMoviesMiddleware.reduce()
//    }
//
//    fun triggerOnPageChanged(index: Int) = intent {
//        discoverMoviesMiddleware.reduce()
//    }
//
//    fun navigateToMovieDetails(movieId: Int) = intent {
//        discoverMoviesMiddleware.reduce()
//    }
}
