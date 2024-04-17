package com.team3.wellness_buddy


import android.content.Context
import android.net.Uri


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.team3.wellness_buddy.usersList.UsersListPage


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val vedioUri= Uri.parse("android.resource://com.team3.wellness_buddy/raw/logo_pulse");
//            FlashScreen()

//            Login()
            UsersListPage()

        }
    }
}



