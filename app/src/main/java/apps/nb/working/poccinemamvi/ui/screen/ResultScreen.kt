package apps.nb.working.poccinemamvi.ui.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import apps.nb.working.poccinemamvi.ui.navigation.Screen


@Composable
fun ResultScreen(//viewModel : ReslutViewModel,
                 navHostController : NavHostController ,
) {



    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = true,
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
    )  { it ->
        Column(modifier = Modifier
            .padding(it)
            .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.height(16.dp))

//            CarouselPhotos(items =  resultMovies ?: emptyList(), onItemClicked = {
//                onMovieClick(it)
//                navController.navigate(AppScreen.MainGraph.DetailScreen.route)
//            })

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cliquez sur un film de la liste pour voir plus d'informations...)",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )



            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                     }, modifier = Modifier
                    .clipToBounds()
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Revenir à la Recherche"
                )
                Text(text = "Revenir à la recherche")

            }

        }
    }
}