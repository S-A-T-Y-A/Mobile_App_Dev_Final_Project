package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@SuppressLint("SuspiciousIndentation")
@Preview
@Composable
fun UsersListPage(){
    val context= LocalContext.current

//        Column{
//
//
//            UserList()
//
//
//        }
//    BottomMenuBar()

    Column(
        modifier = Modifier
            .fillMaxSize().background(Color.LightGray)
        ) {
        NavigationBar {
                Toast.makeText(context,"Menu Clicked",Toast.LENGTH_SHORT).show()
            }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
        UserList()
        BottomMenuBar()

        }





    }



    }
