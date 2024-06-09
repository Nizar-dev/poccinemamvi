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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ResultScreen(
    navHostController: NavHostController,
    //resultMoviesViewModel: ResultMoviesViewModel
) {
    //val discoverMoviesState by resultMoviesViewModel.discoverMoviesState.collectAsState()
    //val moviesPagingData = discoverMoviesState.movies.collectAsLazyPagingItems()
    val isBottomBarVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()

    isBottomBarVisible.value = true
    Scaffold(
        topBar = { CustomTopAppBar(title = "Les films populaires du moment") },
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
                            Screen.MovieScreen(),
                        )
                    )
                }
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Affichage du carousel s'il y a des données disponibles

                // Afficher un indicateur de chargement ou un message d'erreur en cas de données vides
                Text(
                    text = "Aucun film disponible pour le moment.",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Cliquez sur un film de la liste pour voir plus d'informations...)",

            )
            Text(
                text = "Saisir un titre au dessous pour rechercher un film...)",

            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Effectuer une action lorsqu'un bouton est cliqué
                },
                modifier = Modifier
                    .clipToBounds()

            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Nouvelle Recherche"
                )
                Text(text = "Rechercher")

            }

        }

}
