package com.team3.wellness_buddy


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team3.wellness_buddy.register.SignUpPage

import com.team3.wellness_buddy.usersList.UsersListPage
import com.google.firebase.FirebaseApp
import com.google.gson.Gson
import com.team3.wellness_buddy.about.AboutPage
import com.team3.wellness_buddy.categoryexplorer.CategorySelectionPage
import com.team3.wellness_buddy.login.Login
import com.team3.wellness_buddy.usersList.User
object UserPreferences {
    private const val PREF_NAME = "user_preferences"
    private const val KEY_FIRST_NAME = "first_name"
    private const val KEY_LAST_NAME = "last_name"
    private const val KEY_EMAIL = "email"
    private const val KEY_ROLE = "role"

    private const val KEY_USER_SELECTED_CATEGORIES = "user_categories_selected_fetched"


    fun saveSelectedCategories(categories: Set<String>, context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet(KEY_USER_SELECTED_CATEGORIES, categories)
        editor.apply()
    }

    fun getSelectedCategories(context: Context):  Set<String>? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(KEY_USER_SELECTED_CATEGORIES,emptySet())
    }
    fun saveUserInfo(context: Context, firstName: String?, lastName: String?, email: String, role: String?) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_FIRST_NAME, firstName)
        editor.putString(KEY_LAST_NAME, lastName)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_ROLE, role)
        editor.apply()
    }

    fun getFirstName(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_FIRST_NAME, null)
    }

    fun getUserRole(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_ROLE, null)
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
    val context = LocalContext.current
    val navController = rememberNavController()
    val role = UserPreferences.getUserRole(context)
    val email = UserPreferences.getEmail(context)
    var startDestination by remember { mutableStateOf("") }
    if (email != null && email.isNotEmpty()) {
    Log.d("Current Role",role.toString())
        if(role.toString() == "Coach"){
            startDestination = "home"
        }
        else{
            startDestination = "category_explorer"
        }
    } else{
        startDestination = "login"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { Login(navController) }
        composable("home") { UsersListPage(navController) }
        composable("signUp") { SignUpPage(navController) }
        composable("category_explorer"){
            CategorySelectionPage(navController)
        }
        composable("about"){
            AboutPage(navController)
        }
    }

}


