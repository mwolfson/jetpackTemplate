package com.material.demo.ui

import androidx.lifecycle.ViewModel
import com.material.demo.data.ColorItem
import com.material.demo.data.StaticData

class MainViewModel : ViewModel() {
    var colorList = listOf<ColorItem>()

    init {
        colorList = StaticData.COLOR_MAP.values.toList()
    }
}
