package com.team3.wellness_buddy.usersList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.IconText
import com.team3.wellness_buddy.helpers.MyCustomIcon
import com.team3.wellness_buddy.login.Custom_Button
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")

@Composable
fun UserProfile( navController: NavController){

    val coroutineScope = rememberCoroutineScope()

    val user = User(
        firstName = "John",
        lastName = "Doe",
        gender = "Male",
        age = "30",
        email = "john.doe@example.com",
        bio = "Passionate about technology and coding. Love hiking and exploring new places.",
        street = "123 Main Street",
        city = "Anytown",
        zipCode = "12345",
        state = "California",
        country = "USA",
        role = "Coach",
        level = "Expert",
        category = "ENT Specialist"
    )
var add=user.street+", "+user.zipCode+", "+user.state+"\n"+user.country
    val categoryDictionary:Map<String, Int> = mapOf(
        "Psychiatrist" to R.raw.psychiatrist,
        "Orthopedic Specialist" to R.raw.orthopedic,
        "Gastroenterologist" to R.raw.gastroentrologist,
        "Dermatologist" to R.raw.dermatoligist,
        "Neurologist" to R.raw.neurologist,
        "ENT Specialist" to R.raw.ent


    )
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent),
        contentAlignment = Alignment.Center
       )
    {
        Box(modifier = Modifier

            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.9f)
            .background(Custom_Colors.Primary_bg_lite, RoundedCornerShape(20.dp))){




Column {

            Row(modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                ) {
//                ProfilePicture(email = user.email, sizeDp = 100.dp.value.toInt())
                Image(painter = painterResource(id = R.raw.user
                ), contentDescription ="User Profile" , modifier = Modifier.size(60.dp))
                Spacer(modifier = Modifier.width(20.dp))
                val isCoach=if(user.role.equals("Coach"))true else false
                var isExpert=if(user.level.equals("Expert") && isCoach) true else false
                var levelIcon=if(isExpert) R.raw.expert else R.raw.student

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = user.firstName+" "+user.lastName, textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)

                    if(isCoach)
                        user.category?.let { Text(text = it, modifier = Modifier.padding(top = 5.dp), color = Color.White) }
                }

               if(isCoach){
                   val categoryIcon= categoryDictionary[user.category]
                   if (categoryIcon != null) {
                       MyCustomIcon(iconImage = categoryIcon
                       )
                   }
               }
                Spacer(modifier = Modifier.width(20.dp))

                MyCustomIcon(iconImage = levelIcon, modifier = Modifier.weight(0.2f))

            }

            Spacer(modifier = Modifier.height(20.dp))

            Row (modifier = Modifier
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,){
                MyCustomIcon(iconImage = R.raw.email)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = user.email, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row (modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,){
                val gender=if(user.gender.equals("Male")) R.raw.male else R.raw.female
                MyCustomIcon(iconImage = gender)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Age: "+user.age, modifier = Modifier.weight(0.5f), textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier
                .padding(top = 10.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
                ) {
                Text(text = "Bio: ", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = user.bio, color = Color.White)
            }

    Spacer(modifier = Modifier.height(10.dp))

    Row (modifier = Modifier
        .padding(top = 10.dp)
        .padding(horizontal = 30.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,){
        MyCustomIcon(iconImage = R.raw.address, iconSize = 30)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text =add , modifier = Modifier.weight(0.5f), textAlign = TextAlign.Start, fontWeight = FontWeight.Bold, color = Color.White)
    }

    Spacer(modifier = Modifier.height(20.dp))
    Row (modifier = Modifier

        .fillMaxWidth(),
    horizontalArrangement = Arrangement.Center){
        Button(
            onClick = {
                coroutineScope.launch {
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(end = 8.dp)
                .width(100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text("Close", color = Custom_Colors.Primary_bg)
        }
    }

}

        }
    }





}


@Preview(showBackground = true)
@Composable
fun showProfile(){
    UserProfile(navController = rememberNavController())
}