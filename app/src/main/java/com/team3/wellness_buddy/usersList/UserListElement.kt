package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.MyCustomIcon
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale
import kotlin.math.roundToInt


@SuppressLint("ResourceType")
@Composable
fun UserListElement(user: User) {


    val categoryListImages = listOf(
        R.raw.ent,
        R.raw.orthopedic,
        R.raw.gastroentrologist,
        R.raw.dermatoligist,
        R.raw.neurologist,
        R.raw.psychiatrist
    )
    val categoryList= listOf(
        "ENT Specialist",
        "Orthopedic Specialist",
        "Gastroenterologist",
        "Dermatologist",
        "Neurologist",
        "Psychiatrist")

    val categoryDictionary:Map<String, Int> = mapOf(
            "Psychiatrist" to R.raw.psychiatrist,
        "Orthopedic Specialist" to R.raw.orthopedic,
        "Gastroenterologist" to R.raw.gastroentrologist,
        "Dermatologist" to R.raw.dermatoligist,
        "Neurologist" to R.raw.neurologist,
        "ENT Specialist" to R.raw.ent


    )

    var isCoach by remember { mutableStateOf(false) }
    if(user.role.equals("Coach"))
        isCoach=true

    var isExpert by remember {
        mutableStateOf(false)
    }
    if(user.level.equals("Expert"))
        isExpert=true

    val height = 60.dp
    Box(
        modifier = Modifier
            .clickable(onClick = { Log.d("Element clicked", "$user.email is clicked") })
            .fillMaxWidth()
            .padding(5.dp)
            .padding(horizontal = 20.dp)
            .height(height)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),

            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: User profile picture


            ProfilePicture(email = user.email, sizeDp = height.value.toInt()-10)


            // Center: Name and description
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 3.dp)
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


            if(isCoach){
                categoryDictionary[user.category]?.let { MyCustomIcon(iconImage = it) }
                Spacer(modifier = Modifier.width(20.dp))
            }

            val levelIcon=if(isExpert) R.raw.expert else R.raw.student
                MyCustomIcon(iconImage = levelIcon, modifier = Modifier.padding(top = 3.dp))

        }
    }
}


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
@Preview(showBackground = true)
@Composable
fun showElement(){
    UserListElement(user = User())
}