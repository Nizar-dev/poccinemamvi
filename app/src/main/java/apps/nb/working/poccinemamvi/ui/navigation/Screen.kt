package apps.nb.working.poccinemamvi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    open val title: String = "",
    open val icon: ImageVector = Icons.Default.Home,
    open val route: String
) {
    data class MainScreen(
        override val title: String = "Home",
        override val icon: ImageVector = Icons.Filled.Home,
        override val route: String = "main_screen"
    ) : Screen(route = route)

    data class ResultScreen(
        override val title: String = "Films à ce titre",
        override val icon: ImageVector = Icons.Filled.Search,
        override val route: String = "result_screen"
    ) : Screen(route = route)



    data class MovieDetails(
        override val title: String = "movie_details_screen",
        override val icon: ImageVector = Icons.Filled.Info,
        override val route: String = "movie_details"
    ) : Screen(route = route)

}
