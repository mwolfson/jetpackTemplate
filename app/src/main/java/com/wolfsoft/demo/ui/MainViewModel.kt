package com.wolfsoft.demo.ui

import androidx.lifecycle.ViewModel
import com.wolfsoft.demo.data.ColorItem
import com.wolfsoft.demo.data.StaticData

class MainViewModel : ViewModel() {
    var colorList = listOf<ColorItem>()

    init {
        colorList = StaticData.COLOR_MAP.values.toList()
    }
}
