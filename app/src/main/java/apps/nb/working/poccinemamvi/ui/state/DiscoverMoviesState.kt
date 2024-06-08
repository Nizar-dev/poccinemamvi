package apps.nb.working.pocmoviesbymvi.ui.state

import androidx.compose.foundation.lazy.LazyListState
import androidx.paging.PagingData
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class DiscoverMoviesState(
    val currentPageIndex: Int = -1,
    val lastSavedPage: Int = -1,

    val movies: Flow<PagingData<MovieDiscoverItem>> = emptyFlow(), // Flux de données paginées des films découverts
    val lazyListState: LazyListState = LazyListState(), // État de défilement pour la liste des films
    val loading: Boolean = false // Indicateur de chargement
)
