package com.team3.wellness_buddy.usersList

import User
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.ui.theme.Custom_Colors


@SuppressLint("ResourceType")
@Composable
fun UserListElement(user: User) {
    val height = 60.dp
    Box(
        modifier = Modifier
            .border(1.dp, Custom_Colors.Primary_bg_lite, RoundedCornerShape(50.dp))
            .fillMaxWidth(0.95f)
            .padding(5.dp)
            .padding(start = 0.dp)
            .height(height)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: User profile picture
            Image(
                painter = painterResource(id = R.raw.wellness_buddy_bg_image), // Replace with your image resource
                contentDescription = "User profile picture",
                modifier = Modifier
                    .size(height)
                    .clip(CircleShape)
            )

            // Center: Name and description
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.subtitle2
                )
                Text(
                    text = user.category.orEmpty(),
                    style = MaterialTheme.typography.overline,
                    textAlign = TextAlign.Start
                )
            }

            // Right: Favorite icon
            Image(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}