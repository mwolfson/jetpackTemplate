package com.material.demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.material.demo.ui.nav.AppScreens
import com.material.demo.ui.nav.DemoBottomNavigation
import com.material.demo.ui.nav.DemoNavHost
import com.material.demo.ui.nav.DemoTopAppBar
import com.material.demo.ui.theme.ComposeTemplateTheme

@ExperimentalMaterialNavigationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val demoViewModel = MainViewModel()
        setContent {
            PresApp(demoViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("RestrictedApi") // has to do with NavController - check after updating this lib
@ExperimentalMaterialNavigationApi
@Composable
fun PresApp(demoViewModel: MainViewModel) {
    ComposeTemplateTheme {
        val navScreens = listOf(
            AppScreens.HomeNav,
            AppScreens.NavA,
        )

        val navController = rememberNavController()
        val bottomSheetNavigator = rememberBottomSheetNavigator()
        navController.navigatorProvider += bottomSheetNavigator
        ModalBottomSheetLayout(bottomSheetNavigator) {
            Scaffold(
                bottomBar = {
                    DemoBottomNavigation(navController = navController, items = navScreens)
                },
                topBar = {
                    DemoTopAppBar(navController = navController)
                }
            ) { innerPadding ->
                DemoNavHost(
                    navController = navController,
                    demoViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
