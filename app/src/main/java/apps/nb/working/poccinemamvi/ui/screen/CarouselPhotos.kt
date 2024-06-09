package apps.nb.working.poccinemamvi.ui.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import coil.compose.rememberImagePainter
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselPhotos(
    pagingItems: LazyPagingItems<MovieDiscoverItem>,
    onItemClicked: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { pagingItems.itemCount })

    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 24.dp),
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) { pageIndex ->
        val movie = pagingItems[pageIndex]

        movie?.let {
            // Afficher le film dans un élément de la liste
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clickable { onItemClicked(it.id ?: -1) },

            ) {


                Image(
                    painter = rememberImagePainter(
                        data = it.posterPath,
                        builder = {crossfade(true)}
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}