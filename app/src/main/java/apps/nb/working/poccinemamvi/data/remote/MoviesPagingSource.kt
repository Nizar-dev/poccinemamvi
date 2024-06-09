package apps.nb.working.poccinemamvi.data.remote

import android.content.ContentValues.TAG
import android.util.Log
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
            if (response.isSuccessful) {
                val moviesResponse = response.body()
                val movies = convertApiResponseToMovies(moviesResponse ?: return LoadResult.Error(Exception("Null response body")))
                Log.d (TAG, "load: $movies")
                LoadResult.Page(
                    data = movies,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (movies.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(Exception("Error loading movies: ${response.message()}"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, MovieDiscoverItem>): Int? {
        return state.anchorPosition
    }
}
