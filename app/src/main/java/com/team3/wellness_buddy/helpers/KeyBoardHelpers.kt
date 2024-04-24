package com.team3.wellness_buddy.helpers

import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun rememberImeState(): MutableState<Boolean> {

    val imeState= remember {
        mutableStateOf(false)

    }

    val view= LocalView.current
    DisposableEffect(view) {
        val listener= ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyBoardOpen= ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime())?:true
            imeState.value=isKeyBoardOpen
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose{
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}