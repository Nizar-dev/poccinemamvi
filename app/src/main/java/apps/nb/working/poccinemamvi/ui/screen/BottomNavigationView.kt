package apps.nb.working.poccinemamvi.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import apps.nb.working.poccinemamvi.ui.navigation.Screen


@Composable
fun BottomNavigationView(
    navController: NavHostController,
    items: List<Screen>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach {
            BasicItem(
                title = it.title,
                icon = it.icon,
                onClick = {
                    navController.navigate(it.route)
                },
                isSelected = currentRoute == it.route
            )
        }
    }
}

@Composable
fun BasicItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Row(
        modifier = Modifier
            .clip(
                RoundedCornerShape(30.dp)
            )

            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                if (!isSelected) {
                    onClick()
                }
            }
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        Icon(
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
            imageVector = icon,
            contentDescription = "",
            tint = if (isSelected) Color.White else Color.Gray

        )
        if (isSelected) {
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = title,
                color = Color.Black,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
    }
}
