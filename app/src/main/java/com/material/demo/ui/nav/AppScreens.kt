package com.material.demo.ui.nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.ListAlt
import androidx.compose.ui.graphics.vector.ImageVector
import com.material.demo.R

sealed class AppScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object HomeNav : AppScreens("list", R.string.nav_home, Icons.Outlined.ListAlt)
    object NavA : AppScreens("nava", R.string.nav_nava, Icons.Outlined.Bookmarks)
    object Detail : AppScreens("details", R.string.nav_detail, Icons.Filled.Details)
}
