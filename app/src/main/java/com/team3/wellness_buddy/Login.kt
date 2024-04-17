package com.team3.wellness_buddy

import android.annotation.SuppressLint

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.Text
import androidx.compose.material.TextField

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.team3.wellness_buddy.ui.theme.Custom_Colors

import androidx.compose.animation.core.tween
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

@SuppressLint("ResourceType")
@Composable
fun Login(){
    val context = LocalContext.current
    val isImeVisible by rememberImeState()

//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top=if(isImeVisible) 50.dp else 0.dp),
        contentAlignment = (if(isImeVisible) Alignment.TopCenter else Alignment.Center),
    ) {

        Box(modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .fillMaxWidth(0.9f)
            .width(IntrinsicSize.Max)
            .padding(top = 10.dp, bottom = 10.dp)

            ){

        // Content
        Column(
            modifier = Modifier
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = Values.app_name, modifier = Modifier
                ,style = TextStyle(
                    fontSize = 18.sp, // Increase font size to 18 sp
                    fontWeight = FontWeight.Bold // Make text bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Username field

            MyTextField(
                lable = "User Name",
                value = username,
                onValueChange = {username=it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(onDone = null) )

//            var username by remember { mutableStateOf("") }
//            TextField(
//                value = username,
//                onValueChange = { username = it },
//                modifier = Modifier.fillMaxWidth(),
//                label = { Text("Username") }
//            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password field

            MyTextField(lable = "Password",
                value=password,
                onValueChange = {password=it},
                keyboardOptions = KeyboardOptions(keyboardType =KeyboardType.Password),
                keyboardActions = KeyboardActions(onDone=null) )


            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier){
                Custom_Button(text = "login  ",Custom_Colors.Primary_bg_lite) {
                    Toast.makeText(
                        context,
                        "$username Login button clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Custom_Button(text = "Sign Up ",Custom_Colors.Primary_bg_lite) {
                    Toast.makeText(
                        context,
                        " $password Sign Up button clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Forgot password link
            Text(
                text = "Forgot Password?",
                color = Color.Blue,
                modifier = Modifier.clickable { /* Handle forgot password */ }
            )
        }
        }
    }
}

@Composable
fun Custom_Button(text: String, bg_color:Color,onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier

            .height(IntrinsicSize.Min)
            .padding(end = 8.dp)
            .width(100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = bg_color)
    ) {
        Text(text, color = Color.White)
    }
}