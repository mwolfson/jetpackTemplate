package com.material.demo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.material.demo.data.ColorItem
import com.material.demo.data.StaticData
import com.material.demo.ui.nav.NavigationCommand
import com.material.demo.ui.nav.NavigationManager

class MainViewModel : ViewModel() {
    var navigationManager = NavigationManager()

    var colorList = listOf<ColorItem>()

    init {
        colorList = StaticData.COLOR_MAP.values.toList()
    }

    fun navigate(navigationCommand: NavigationCommand) {

    }


}
