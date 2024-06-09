package apps.nb.working.poccinemamvi.ui.screen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import apps.nb.working.poccinemamvi.domain.model.MovieDetails
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    selectedMovie: MovieDetails
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Le titre de votre film : ${selectedMovie.title}",
                    fontSize = 24.sp
                )
            })
        },
        bottomBar = {Button(
            onClick = {  },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "Revenir à la page d'accueil")
        } }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Le synopsis de votre film :\n${selectedMovie.overview}",
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "La date de sortie de votre film : ${selectedMovie.releaseDate}",
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "La note des spectateurs de votre film : ${selectedMovie.voteAverage}",
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = rememberImagePainter(
                        data = selectedMovie.posterPath,
                        builder = { crossfade(true) }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}
