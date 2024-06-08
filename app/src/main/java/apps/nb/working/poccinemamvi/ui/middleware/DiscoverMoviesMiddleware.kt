package apps.nb.working.pocmoviesbymvi.ui.middleware
import androidx.paging.PagingData
import androidx.paging.cachedIn
import apps.nb.working.poccinemamvi.domain.model.IDiscoverMoviesMiddleware
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import apps.nb.working.poccinemamvi.domain.usecases.GetDiscoverMoviesUseCase
import apps.nb.working.poccinemamvi.ui.middleware.BaseMiddleware
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.pocmoviesbymvi.ui.state.MovieDetailsState
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffects
import apps.nb.working.pocmoviesbymvi.ui.viewmodel.DiscoverMoviesSideEffectsMiddleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.annotation.OrbitInternal
import javax.inject.Inject

class DiscoverMoviesMiddleware @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
) : BaseMiddleware(), IDiscoverMoviesMiddleware {

    private lateinit var container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>
    private lateinit var viewModelScope: CoroutineScope
    lateinit var getDiscoverMovies: Flow<PagingData<MovieDiscoverItem>>

    @OptIn(OrbitInternal::class)
    override fun init(
        container: Container<MutableStateFlow<AppState>, DiscoverMoviesSideEffects>,
        viewModelScope: CoroutineScope,
        callback: (Flow<PagingData<MovieDiscoverItem>>) -> Unit
    ) {
        this.container = container
        this.viewModelScope = viewModelScope

        viewModelScope.launch {
            // Récupérer les films sans tenir compte des genres
            getDiscoverMovies = getDiscoverMoviesUseCase.getMovies()
                .cachedIn(viewModelScope)

            // Passer le flux de données paginées au callback
            callback(getDiscoverMovies)

            // Mettre à jour l'état dans le container
            container.orbit {
                reduce {
                    state.value = state.value.copy(
                        homeState = state.value.homeState.copy(
                            discoverMoviesState = state.value.homeState.discoverMoviesState.copy(
                                movies = getDiscoverMovies
                            )
                        )
                    )
                    state
                }
            }
        }
    }

    override fun reduce(discoverMiddlewaresMoviesSideEffects: DiscoverMoviesSideEffectsMiddleware) {
        when (discoverMiddlewaresMoviesSideEffects) {
            is DiscoverMoviesSideEffectsMiddleware.TriggerOnPageChanged -> {
                triggerOnPageChanged(discoverMiddlewaresMoviesSideEffects.index)
            }
            is DiscoverMoviesSideEffectsMiddleware.GetCurrentDiscoverPageAndScrollOffset -> {
                getCurrentPageAndScrollOffset()
            }
            is DiscoverMoviesSideEffectsMiddleware.NavigateToMovie -> {
                navigateToMovieDetails(discoverMiddlewaresMoviesSideEffects.movieId)
            }
            is DiscoverMoviesSideEffectsMiddleware.SetLastScrolledPage -> {
                setLastScrolledPage(discoverMiddlewaresMoviesSideEffects.page)
            }
        }
    }

    @OptIn(OrbitInternal::class)
    fun getCurrentPageAndScrollOffset() = viewModelScope.launch {
        container.orbit {
            val page = state.value.homeState.discoverMoviesState.lastSavedPage.takeIf { it > 0 } ?: 0
            postSideEffect(
                DiscoverMoviesSideEffects.GetCurrentDiscoverPageAndScrollOffset(page)
            )
        }
    }

    @OptIn(OrbitInternal::class)
    fun setLastScrolledPage(index: Int) = viewModelScope.launch {
        container.orbit {
            if (index != state.value.homeState.discoverMoviesState.lastSavedPage) {
                reduce {
                    state.value = state.value.copy(
                        homeState = state.value.homeState.copy(
                            discoverMoviesState = state.value.homeState.discoverMoviesState.copy(
                                lastSavedPage = index
                            )
                        )
                    )
                    state
                }
            }
        }
    }

    @OptIn(OrbitInternal::class)
    fun triggerOnPageChanged(index: Int) = viewModelScope.launch {
        container.orbit {
            if (index != state.value.homeState.discoverMoviesState.currentPageIndex) {
                reduce {
                    state.value = state.value.copy(
                        homeState = state.value.homeState.copy(
                            discoverMoviesState = state.value.homeState.discoverMoviesState.copy(
                                currentPageIndex = index
                            )
                        )
                    )
                    state
                }
                postSideEffect(DiscoverMoviesSideEffects.TriggerOnPageChanged(index))
            }
        }
    }

    @OptIn(OrbitInternal::class)
    fun navigateToMovieDetails(movieId: Int) = viewModelScope.launch {
        container.orbit {
            reduce {
                state.value = state.value.copy(
                    movieDetailsState = state.value.movieDetailsState + MovieDetailsState(id = movieId),
                )
                state
            }
            postSideEffect(DiscoverMoviesSideEffects.NavigateToMovie)
        }
    }
}
