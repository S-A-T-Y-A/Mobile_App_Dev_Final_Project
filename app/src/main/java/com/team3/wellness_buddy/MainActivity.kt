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
import com.team3.wellness_buddy.login.Login

object UserPreferences {
    private const val PREF_NAME = "user_preferences"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_LAST_NAME = "last_name"
    private const val KEY_EMAIL = "email"

    fun saveUserInfo(context: Context, firstName: String?, lastName: String?, email: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_FIRST_NAME, firstName)
        editor.putString(KEY_LAST_NAME, lastName)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    fun getFirstName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_FIRST_NAME, null)
    }

    fun getLastName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_LAST_NAME, null)
    }

    fun getEmail(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_EMAIL, null)
    }
}

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


