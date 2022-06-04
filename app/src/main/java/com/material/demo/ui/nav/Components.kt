package com.material.demo.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.material.demo.ui.theme.margin_half

@Composable
fun DemoBottomNavigation(
    navController: NavHostController,
    items: List<AppScreens>
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val route = navBackStackEntry?.destination
        items.forEach { screen ->
            val isSelected: Boolean =
                route?.hierarchy?.any { it.route == screen.route } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = screen.resourceId.toString()
                    )
                },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = isSelected,
                alwaysShowLabel = false,
                onClick = {
                    // If on current screen, ignore button press to avoid redraw
                    if (screen.route != route?.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
fun DemoTopAppBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    SmallTopAppBar(
        navigationIcon = {
            if (route.equals("list") || route.equals("nava")) {
                val iconToSet =
                    when (route) {
                        "list" -> AppScreens.HomeNav.icon
                        "nava" -> AppScreens.NavA.icon
                        else -> AppScreens.Detail.icon
                    }
                Icon(
                    imageVector = iconToSet,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = margin_half)
                )
            }
        },
        title = {
            if (route.equals("list") || route.equals("nava")) {
                Text(route.orEmpty().uppercase())
            }
        },

        // Material design suggests avoiding large areas of bright colors in dark theme. A common
        // pattern is to color a container primary color in light theme and surface color in dark
        // themes; many components use this strategy by default e.g. App Bars and Bottom Navigation.
        // To make this easier to implement, Colors offers a primarySurface color which provides
        // exactly this behaviour and these components use by default.
//        backgroundColor = MaterialTheme.colorScheme.primaryContainer
    )
}