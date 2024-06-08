package apps.nb.working.poccinemamvi.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem

import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class MoviesPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, MovieDiscoverItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDiscoverItem> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = apiService.getDiscoverMovies(page = position)
            val movies = response.body()?.results?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDiscoverItem>): Int? {
        return state.anchorPosition
    }
}