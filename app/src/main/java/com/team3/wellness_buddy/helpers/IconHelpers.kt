package com.team3.wellness_buddy.helpers

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun loadMyIcon(iconImage:Int, altText:String="image"): ImageBitmap {
    val context= LocalContext.current
    val bitmap = BitmapFactory.decodeStream(context.resources.openRawResource(iconImage))
    return bitmap.asImageBitmap()
}

@Composable
fun MyCustomIcon(
    iconImage: ImageBitmap,
    modifier: Modifier = Modifier,
    iconSize: Int = 24 // Adjust the size of the icon as needed
) {
    Box(modifier = modifier
        ) {
        Image(
            bitmap = iconImage,
            contentDescription = null,
            modifier = Modifier
                .size(iconSize.dp)
                .fillMaxSize()// Set the size of the icon
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,

        )
    }
}

@Composable
fun MyCustomIcon(
    iconImage: Int,
    modifier: Modifier = Modifier,
    iconSize: Int = 24 // Adjust the size of the icon as needed
) {
    Box(modifier = modifier
    ) {
        Image(
            bitmap = loadMyIcon(iconImage = iconImage),
            contentDescription = null,
            modifier = Modifier
                .size(iconSize.dp)
                .fillMaxSize()// Set the size of the icon
                .background(Color.Transparent),
            contentScale = ContentScale.Fit,

            )
    }
}