package com.material.demo.ui.nav

import androidx.navigation.NamedNavArgument

object NavigationDirections {

    val default = object: NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()

        override val destination: String = ""
    }

    val home = object: NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()

        override val destination: String = "list"
    }

    val feata = object: NavigationCommand {
        override val arguments= emptyList<NamedNavArgument>()

        override val destination: String = "feata"
    }

    val detail = object: NavigationCommand {
        override val arguments= emptyList<NamedNavArgument>()

        override val destination: String = "details"
    }

}