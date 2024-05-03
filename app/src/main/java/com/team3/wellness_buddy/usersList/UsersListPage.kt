package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.getWindowStatusBarHeight
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.register.MyCustomIcon
import com.team3.wellness_buddy.register.SignUpForm
import com.team3.wellness_buddy.register.loadMyIcon
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.team3.wellness_buddy.UserPreferences


@SuppressLint("SuspiciousIndentation", "ResourceType")

@Composable
fun UsersListPage(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
   Scaffold(
        modifier = Modifier
            .padding(
                top = getWindowStatusBarHeight(),
                bottom = getWindowToolBarHeight() + 10.dp
            ),

        topBar = {
            TopAppBar(
                {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                    ) {
                        val firstName = UserPreferences.getFirstName(context)
                        val lastName = UserPreferences.getLastName(context)

                        val displayName = if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
                            "Wellness Buddy"
                        } else {
                            "${firstName.orEmpty()} ${lastName.orEmpty()}"
                        }

                        Text(text = displayName)
                    }
                },
                backgroundColor = Custom_Colors.Primary_bg,
                contentColor = Color.White,
                actions = {
                    IconButton(onClick = { /* Handle left icon click */ }) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },


            )
        },

       floatingActionButton = {

//           val myIconBitmap= loadMyIcon(iconImage = R.raw.user, altText = "ProfilePicture")
           FloatingActionButton(
               onClick = {
                   coroutineScope.launch {
                       navController.navigate("login")
                   }
               },
               modifier = Modifier
                   .size(50.dp),
               backgroundColor = Color.Black, // Set your desired FAB background color // Set your desired FAB content color
               elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
//               shape = CircleShape
           ) {

               Image(
                   modifier = Modifier.
                   fillMaxSize(),
                   painter = painterResource(id = R.raw.user), contentDescription ="user" )
           }
       },
       floatingActionButtonPosition = FabPosition.Center,

    ) { innerPadding ->

       UserListContent(innerPadding)
   }}
