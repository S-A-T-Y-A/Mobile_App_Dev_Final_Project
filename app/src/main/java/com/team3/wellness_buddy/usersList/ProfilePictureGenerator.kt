package com.team3.wellness_buddy.usersList

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale
import kotlin.math.roundToInt


fun getGravatar(email: String, size: Int = 80): String {
    val defaultImage = "identicon"
    val gravatarUrl = "https://www.gravatar.com/avatar/"
    val hash = md5(email.trim().toLowerCase(Locale.ROOT).toByteArray())
    return "$gravatarUrl$hash?s=$size&d=$defaultImage"
}


fun md5(input: ByteArray): String {
    val md = MessageDigest.getInstance("MD5")
    val md5Bytes = md.digest(input)
    val bigInt = BigInteger(1, md5Bytes)
    var md5 = bigInt.toString(16)
    // Pad to make it 32 characters long
    while (md5.length < 32) {
        md5 = "0$md5"
    }
    Log.d("MD5 image url", md5)
    return md5
}

//fun main() {
//    val email = "example@example.com"
//    val profilePictureUrl = getGravatar(email)
//    println("Profile picture URL: $profilePictureUrl")
//}
@Composable
fun ProfilePicture(email: String, sizeDp: Int) {
    val density = Resources.getSystem().displayMetrics.density
    val imageSizePx = (sizeDp * density).roundToInt()

    val gravatarUrl = getGravatar(email, imageSizePx)
    val painter = rememberImagePainter(gravatarUrl)

    Image(
        painter = painter,
        contentDescription = "User profile picture",
        modifier = Modifier
            .size(sizeDp.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, RoundedCornerShape(30.dp))
    )
}