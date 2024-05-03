package com.team3.wellness_buddy.helpers

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.helpers.MyCustomIcon
import com.team3.wellness_buddy.helpers.loadMyIcon


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