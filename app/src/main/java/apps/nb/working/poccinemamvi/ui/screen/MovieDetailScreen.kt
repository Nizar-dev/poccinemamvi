package apps.nb.working.poccinemamvi.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import apps.nb.working.poccinemamvi.ui.navigation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(navHostController: NavHostController,
                      //movieDetailsViewModel: MovieDetailsViewModel
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
                            Screen.MovieScreen(),
                        )
                    )
                }
            }
        }
    )  {

    }
}