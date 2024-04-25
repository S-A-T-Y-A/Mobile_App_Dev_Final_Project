package com.team3.wellness_buddy


import android.content.Context
import android.net.Uri


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team3.wellness_buddy.register.SignUpPage

import com.team3.wellness_buddy.usersList.UsersListPage
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellnessBuddyApp()
            FirebaseApp.initializeApp(this)
        }
    }
}



@Composable
fun WellnessBuddyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("login") { Login(navController) }
        composable("home") { UsersListPage(navController) }
        composable("signUp") { SignUpPage(navController) }
    }
}


