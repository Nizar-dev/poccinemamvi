package apps.nb.working.poccinemamvi.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import apps.nb.working.poccinemamvi.ui.navigation.Screen
import apps.nb.working.poccinemamvi.ui.viewmodel.DiscoverMoviesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navHostController: NavHostController,
    discoverMoviesViewModel: DiscoverMoviesViewModel
) {
    val isBottomBarVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    isBottomBarVisible.value = true
    Scaffold(
       bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                Column() {
                    BottomNavigationView(
                        navController = navHostController,
                        listOf(
                            Screen.MainScreen(),
                            Screen.ResultScreen(),
                            Screen.MovieDetails(),
                        )
                    )
                }
            }
        }
    ) {
        Scaffold { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(16.dp))
//            CarouselPhotos(items = popularMovies ?: emptyList(), onItemClicked = {
//                onMovieClick(it)
//                navController.navigate(AppScreen.MainGraph.DetailScreen.route)
//            })


            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cliquez sur un film de la liste pour voir plus d'informations...)",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Saisir un titre au dessous pour rechercher un film...)",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
//            OutlinedTextField(
//                value = searchText.value,
//                onValueChange = {    searchText.value = it
//                    onTextChange(searchText.value)},
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                label = {
//                    Text( text = "Saisir le titre du film")
//                },)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
//                    Log.d("MainScreenButton", "titre recherché: ${searchText.value}")
//                    onButtonClick(searchText.value)
//                    navController.navigate(AppScreen.MainGraph.ResultScreen.route)
                },
                modifier = Modifier
                    .clipToBounds()
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Nouvelle Recherche"
                )
                Text(text = "Rechercher")

            }

        }
    }
}
    }
