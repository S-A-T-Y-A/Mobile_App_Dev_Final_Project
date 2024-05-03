package com.team3.wellness_buddy.login

import android.annotation.SuppressLint
import android.util.Log

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.Text

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import com.team3.wellness_buddy.ui.theme.Custom_Colors

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.UserPreferences
import com.team3.wellness_buddy.helpers.MyCustomIcon
import com.team3.wellness_buddy.helpers.rememberImeState
import kotlinx.coroutines.launch
import java.security.MessageDigest

@SuppressLint("ResourceType")
@Composable
fun Login(navController: NavController){
    val context = LocalContext.current
    val isImeVisible by rememberImeState()

//    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val openDialog = remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    fun validateLoginForm(): Boolean {
        return when {
            username.isEmpty() -> {
                dialogMessage.value = "Please enter Username"
                openDialog.value = true
                false
            }
            password.isEmpty() -> {
                dialogMessage.value = "Please enter Password"
                openDialog.value = true
                false
            }
            else -> true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Custom_Colors.Primary_bg)
            .padding(top = if (isImeVisible) 50.dp else 0.dp),
        contentAlignment = (if(isImeVisible) Alignment.TopCenter else Alignment.Center),
    ) {


//        FlashScreen()

        Box(modifier = Modifier
            .background(Custom_Colors.light_bg_secondary, RoundedCornerShape(30.dp))
            .fillMaxWidth(0.9f)
            .width(IntrinsicSize.Max)
            .padding(top = 10.dp, bottom = 10.dp),

            ){


//            FlashScreen()
        // Content
        Column(
            modifier = Modifier
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {

                    MyCustomIcon(iconImage = R.raw.logo, iconSize = 50)
                   Spacer(modifier = Modifier.width(2.dp))
                Text(text = "Wellness Buddy",
                        fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)

            }


//            val myIconBitmap= loadMyIcon(iconImage = R.raw.log, altText = iconText+"_icon")
//            Row(
//                Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
//            ){
//                MyCustomIcon(iconImage = myIconBitmap)
//                Spacer(modifier = modifier.width(20.dp))
//                androidx.compose.material3.Text(text = text, fontWeight = FontWeight.Bold)
//            }

            Spacer(modifier = Modifier.height(30.dp))
            // Username field

            MyTextField(
                lable = "User Name",
                value = username,
                onValueChange = {username=it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                keyboardActions = KeyboardActions(onDone = null),
                iconImage = R.raw.email)

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
                keyboardActions = KeyboardActions(onDone=null) ,
                iconImage = R.raw.password)


            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier){
                Custom_Button(text = "login  ",Custom_Colors.Primary_bg_lite) {
                    if (validateLoginForm()) {
                        // Check user data in Firebase here
                        checkUserInFirebase(username, hashString(password), context, navController)
                    }
                }

                Custom_Button(text = "Sign Up ",Custom_Colors.Primary_bg_lite) {
                    coroutineScope.launch {
                        navController.navigate("signUp")
                    }
                    Toast.makeText(
                        context,
                        " $password Sign Up button clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Forgot password link
            Row {
                
            Text(
                text = "Forgot Password?",
                color = Color.Blue,
                modifier = Modifier.clickable { /* Handle forgot password */ }
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Back to users
            Text(
                text = "Back To Coaches",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        navController.navigate("home")
                    }
                }
            )
            }
        }
        }
    }
}

fun checkUserInFirebase(username: String, password: String, context: android.content.Context, navController:NavController) {
    lateinit var firebaseRef : DatabaseReference
    firebaseRef = FirebaseDatabase.getInstance().getReference("users")
    Log.d("LoginForm",username )

    firebaseRef.orderByChild("email").equalTo(username).addListenerForSingleValueEvent (
        object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(User::class.java)
                        Log.d("LoginForm",user.toString())
                        if (user?.hashedPassword == password) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            UserPreferences.saveUserInfo(context, user.firstName, user.lastName, user.email)

                            Log.d("LoginForm","loggedIn" )
                            navController.navigate("home")
                            return
                        }
                    }
                    Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Error occurred", Toast.LENGTH_SHORT).show()
                Log.d("LoginForm","Error" )
            }
        }
    )
}

fun hashString(input: String): String {
    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = MessageDigest
        .getInstance("SHA-256")
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt() and 0xff
        result.append(HEX_CHARS[i shr 4])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}


data class User(
    val email: String = "",
    val hashedPassword: String = "",
    val firstName: String = "",
    val lastName: String = ""
)

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


@Preview(showBackground = true)
@Composable
fun showLogin(){
    Login(navController = rememberNavController())
}