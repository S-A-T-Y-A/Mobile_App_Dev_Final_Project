package com.team3.wellness_buddy.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.team3.wellness_buddy.R


@SuppressLint("ResourceType")
@Composable
fun FlashScreen() {
    val context = LocalContext.current

    Box(
        modifier = Modifier,

    ){
        // Background image
        Image(
            painter = painterResource(id = R.raw.wellness_buddy_bg_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()


        )
    }
    val navController = rememberNavController()

}