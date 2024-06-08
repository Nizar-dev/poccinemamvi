package apps.nb.working.poccinemamvi.data.remote
import androidx.paging.PagingSource
import androidx.paging.PagingState
import apps.nb.working.poccinemamvi.domain.model.MovieDiscoverItem
import retrofit2.HttpException
import java.io.IOException

class SearchMoviesPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, MovieDiscoverItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDiscoverItem> {
        val position = params.key ?: 1

        return try {
            val response = apiService.getMoviesByTitle(query, page = position)
            val movies = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (position == 1) null else position - 1,
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
