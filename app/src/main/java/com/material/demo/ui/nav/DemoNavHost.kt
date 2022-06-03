package com.material.demo.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.material.demo.data.ColorItem
import com.material.demo.data.StaticData
import com.material.demo.ui.MainViewModel
import com.material.demo.ui.detail.DetailBody
import com.material.demo.ui.feata.FeatABody
import com.material.demo.ui.home.ColorListBody

@ExperimentalMaterialNavigationApi
@Composable
fun DemoNavHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeNav.route,
        modifier = modifier
    ) {
        composable(route = AppScreens.HomeNav.route) {
            ColorListBody(
                onItemClicked = {
                    navController.navigate("${AppScreens.Detail.route}${it.name}")
                },
                viewModel.colorList,
            )
        }
        composable(route = AppScreens.NavA.route) {
            FeatABody(
                onLaunchClicked = {
                    navController.navigate("${AppScreens.Detail.route}$it")
                }
            )
        }
        //  adb shell am start -a android.intent.action.VIEW -d "http://www.m3demo.com/details/red"
        bottomSheet(
            route = "${AppScreens.Detail.route}{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "{name}"
                    uriPattern = "https://www.m3demo.com/details/{name}"
                }
            )
        ) { entry ->
            // Send ColorItem, or default if one is not found with that name
            val nameEntry = entry.arguments?.getString("name") ?: "None Found"
            val notFound = ColorItem(
                "Color Not Found - $nameEntry",
                "https://placehold.co/64x64/9e9e9e/9e9e9e.png",
                "https://placehold.co/128x256/9e9e9e/9e9e9e.png"
            )
            val colorItem = StaticData.COLOR_MAP[nameEntry.lowercase()] ?: notFound
            DetailBody(
                colorItem = colorItem
            )
        }
    }
}
