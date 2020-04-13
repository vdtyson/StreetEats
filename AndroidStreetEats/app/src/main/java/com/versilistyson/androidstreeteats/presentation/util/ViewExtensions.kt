package com.versilistyson.androidstreeteats.presentation.util

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import com.google.android.material.bottomnavigation.BottomNavigationView
// Toolbar
fun Toolbar.swapMenu(menuId: Int) {
    clearMenu()
    inflateMenu(menuId)
}
fun Toolbar.clearMenu() {
    if(menu.isNotEmpty()) {
        menu.clear()
    }
}
// Bottom Nav
fun BottomNavigationView.swapMenu(menuId: Int) {
    clearMenu()
    inflateMenu(menuId)
}
fun BottomNavigationView.clearMenu() {
    if(menu.isNotEmpty()) {
        menu.clear()
    }
}
