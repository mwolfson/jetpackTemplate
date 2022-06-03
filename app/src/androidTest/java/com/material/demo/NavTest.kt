package com.material.demo

import android.content.res.Resources
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.material.demo.ui.MainViewModel
import com.material.demo.ui.nav.DemoNavHost
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalMaterialNavigationApi::class)
class NavTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: NavHostController
    private lateinit var viewModel: MainViewModel
    private lateinit var res: Resources

    @Before
    fun setup() {
        viewModel = MainViewModel()
        res = InstrumentationRegistry.getInstrumentation().targetContext.resources

        composeTestRule.setContent {
            navController = rememberNavController()

            DemoNavHost(navController = navController, viewModel = viewModel)
        }
    }

//    @Test
//    fun forNavHost_whenInitialized_verifyDestination() {
// TODO Fix: Could not find Navigator with name "BottomSheetNavigator". You must call NavController.addNavigator() for each navigation type.
//
//        navController.addNavigator() // ???
//        composeTestRule
//            .onNodeWithContentDescription(res.getString(R.string.nav_nava))
//           .assertIsDisplayed()
//    }
}
