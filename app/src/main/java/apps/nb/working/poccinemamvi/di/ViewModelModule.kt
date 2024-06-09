package apps.nb.working.poccinemamvi.di

import apps.nb.working.poccinemamvi.data.remote.ApiService
import apps.nb.working.poccinemamvi.data.repository.MoviesRemoteRepo
import apps.nb.working.poccinemamvi.data.repository.MoviesRemoteRepoImpl
import apps.nb.working.poccinemamvi.domain.usecases.GetDiscoverMoviesUseCase
import apps.nb.working.poccinemamvi.ui.middleware.DiscoverMoviesMiddleware
import apps.nb.working.poccinemamvi.ui.state.AppState
import apps.nb.working.poccinemamvi.ui.viewmodel.DiscoverMoviesViewModel
import apps.nb.working.pocmoviesbymvi.ui.state.DiscoverMoviesState
import apps.nb.working.pocmoviesbymvi.ui.state.MovieDetailsState
import apps.nb.working.pocmoviesbymvi.ui.state.ResultState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideAppState(): MutableStateFlow<AppState> {
        return MutableStateFlow(AppState()) // Ici, nous fournissons l'état de l'application
    }
    @Provides
    @ViewModelScoped
    fun provideDiscoverMoviesState(): MutableStateFlow<DiscoverMoviesState> {
        return MutableStateFlow(DiscoverMoviesState())
    }

    @Provides
    @ViewModelScoped
    fun provideMovieDetailsState(): MutableStateFlow<MovieDetailsState> {
        return MutableStateFlow(MovieDetailsState())
    }

    @Provides
    @ViewModelScoped
    fun provideResultState(): MutableStateFlow<ResultState> {
        return MutableStateFlow(ResultState())
    }

    @Provides
    @ViewModelScoped
    fun provideDiscoverMoviesMiddleware(
        getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
    ): DiscoverMoviesMiddleware {
        return DiscoverMoviesMiddleware(getDiscoverMoviesUseCase)
    }

    @Provides
    @ViewModelScoped
    fun provideGetDiscoverMoviesUseCase(repository:  MoviesRemoteRepo): GetDiscoverMoviesUseCase {
        return GetDiscoverMoviesUseCase(repository)
    }
    @Provides
   @ViewModelScoped
    fun provideMoviesRemoteRepo(apiService: ApiService): MoviesRemoteRepo {
        return MoviesRemoteRepoImpl(apiService)
    }

}
