package com.team3.wellness_buddy.register

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun IconText(
    modifier: Modifier,
    iconImage:Int,
    iconText:String,
    text:String

){
    val myIconBitmap= loadMyIcon(iconImage = iconImage, altText = iconText+"_icon")
    Row(
        Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ){
        MyCustomIcon(iconImage = myIconBitmap)
        Spacer(modifier = modifier.width(20.dp))
        Text(text = text, fontWeight = Bold)
    }
}