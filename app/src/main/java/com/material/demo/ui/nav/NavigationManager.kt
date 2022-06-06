package com.material.demo.ui.nav

import com.material.demo.ui.nav.NavigationDirections.default
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    private var commands = MutableStateFlow(default)

    fun navigate (
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}