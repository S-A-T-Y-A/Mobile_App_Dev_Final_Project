package com.team3.wellness_buddy

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("InternalInsetResource", "DiscouragedApi")
fun getStatusBarHeight(context: Context): Int {
    var result = 0
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

@SuppressLint("DiscouragedApi")
fun getBottomToolBarHeight(context: Context):Int{
    var result=0
    val resourceId = context.resources.getIdentifier("nav_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = context.resources.getDimensionPixelSize(resourceId)
    }
    return result

}