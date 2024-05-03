package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.getWindowStatusBarHeight
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch
import androidx.navigation.NavController


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
                        Text(text = "User Name")
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
