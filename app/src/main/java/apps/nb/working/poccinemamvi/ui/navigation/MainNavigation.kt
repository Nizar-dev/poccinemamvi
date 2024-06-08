package apps.nb.working.poccinemamvi.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apps.nb.working.poccinemamvi.ui.screen.MainScreen
import apps.nb.working.poccinemamvi.ui.screen.MovieDetailsView
import apps.nb.working.poccinemamvi.ui.screen.ResultScreen
import apps.nb.working.poccinemamvi.ui.viewmodel.DiscoverMoviesViewModel


@Composable
fun MainNavigation() {
    val navHostController = rememberNavController()
    val discoverMoviesViewModel: DiscoverMoviesViewModel  = hiltViewModel()


    //val searchViewModel: ResultViewModel = hiltViewModel()

    Scaffold (){
        Box(modifier = Modifier.padding(it))
        {
            NavHost (navController = navHostController,
                startDestination = Screen.MainScreen().route){

                composable(route = Screen.MainScreen().route) {
                    MainScreen(navHostController = navHostController,
                        discoverMoviesViewModel = discoverMoviesViewModel)
                }
                composable(route = Screen.ResultScreen().route) {
                    ResultScreen(
                        //viewModel = searchViewModel,
                        navHostController = navHostController
                    )
                }

                composable(route = Screen.MovieDetails().route) {
                    MovieDetailsView(navHostController = navHostController)                }

            }
        }
    }

}

