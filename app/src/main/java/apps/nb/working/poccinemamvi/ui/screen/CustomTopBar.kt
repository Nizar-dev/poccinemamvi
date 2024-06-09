package apps.nb.working.poccinemamvi.ui.screen


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String
) {
    TopAppBar(
        title = { Text(
            text = "Cinema and Popular Movies",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        ) },
        navigationIcon = {
            IconButton(onClick = { },
                modifier = Modifier
                    .height(60.dp)) {
                Icon(Icons.Filled.Home, contentDescription = "Back")
            }
        },

    )
}