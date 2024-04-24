package com.team3.wellness_buddy.helpers

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@SuppressLint("InternalInsetResource", "DiscouragedApi")
private fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

@SuppressLint("DiscouragedApi")
private fun getBottomToolBarHeight(context: Context):Int{
    var result=0
    val resourceId = context.resources.getIdentifier("nav_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result

}

@Composable
fun getWindowStatusBarHeight(): Dp {
    val statusBarHeight = getStatusBarHeight(LocalContext.current)
    val statusBarHeightDp = with(LocalDensity.current) {
        statusBarHeight.toDp()
    }
    return statusBarHeightDp
}

@Composable
fun getWindowToolBarHeight():Dp{
    val btmToolBarHeight= getBottomToolBarHeight(LocalContext.current)
    val btmToolBarHeightDp=with(LocalDensity.current){
        btmToolBarHeight.toDp()
    }
    return btmToolBarHeightDp
}