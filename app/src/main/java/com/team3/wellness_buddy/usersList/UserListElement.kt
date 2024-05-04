package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.IconText
import com.team3.wellness_buddy.helpers.MyCustomIcon
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Locale
import kotlin.math.roundToInt


@SuppressLint("ResourceType")
@Composable
fun UserListElement(user: User, navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Patient")
            },
            text = {
                // Display user details here
                Column {
                    Text("Name: ${user.firstName} ${user.lastName}")
                    Text("Gender: ${user.gender}")
                    Text("Age: ${user.age}")
                    Text("Email: ${user.email}")
                    Text("Bio: ${user.bio}")
                    Text("Address: ${user.street}, ${user.city}, ${user.state}, ${user.country}")
                    Text("Role: ${user.role}")
                    if (user.category?.isNotBlank() == true) {
                        Text("Category: ${user.category}")
                    }
                    if (user.level?.isNotBlank() == true) {
                        Text("Level: ${user.level}")
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Close")
                }
            }
        )
    }
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
            .clickable(onClick = {
                showDialog = true
            })
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
                    .padding(bottom = 3.dp, end = 20.dp)
            ) {
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(3.dp))
                val description=if(isCoach) user.category.orEmpty() else user.bio
                Text(
                    text = description,
                    style = MaterialTheme.typography.overline,
                    textAlign = TextAlign.Start
                )
            }


            if(isCoach){
                categoryDictionary[user.category]?.let { MyCustomIcon(iconImage = it) }
                Spacer(modifier = Modifier.width(20.dp))
                val levelIcon=if(isExpert) R.raw.expert else R.raw.student
                MyCustomIcon(iconImage = levelIcon, modifier = Modifier.padding(top = 3.dp))
            }else{

                val gender=if(user.gender.equals("Male"))R.raw.male else R.raw.female
                
                Row( modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                    MyCustomIcon(iconImage = gender, modifier = Modifier.padding(top = 5.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                    MyCustomIcon(iconImage = R.raw.age)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = ": "+user.age, modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight.Bold)
                }

            }

//            Text(text = user.age)


        }
    }
}


