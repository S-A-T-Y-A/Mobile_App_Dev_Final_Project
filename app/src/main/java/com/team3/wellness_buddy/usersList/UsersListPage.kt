package com.team3.wellness_buddy.usersList




import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.getWindowStatusBarHeight
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.team3.wellness_buddy.UserPreferences



//user list -dummy //0
//
//fun fetchUserList() {
//    val firebaseRef = FirebaseDatabase.getInstance().getReference("users")
//    firebaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
//        override fun onDataChange(snapshot: DataSnapshot) {
//            val userList = mutableListOf<User>()
//            for (userSnapshot in snapshot.children) {
//                val user = userSnapshot.getValue(User::class.java)
//                user?.let {
//                    userList.add(it)
//                }
//            }
//            Log.d("UserList",userList.toString())
//            _userList.value = userList
//            isDataLoaded = true
//        }
//
//        override fun onCancelled(error: DatabaseError) {
//            // Handle error
//        }
//    })
//}


fun fetchUserList(): MutableList<User> {
    val firebaseRef = FirebaseDatabase.getInstance().getReference("users")
    val userList = mutableListOf<User>()
    firebaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            for (userSnapshot in snapshot.children) {
                val user = userSnapshot.getValue(User::class.java)
                user?.let {
                    userList.add(it)
                }
            }
            Log.d("UserList",userList.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error
        }
    })
    return userList;
}

@SuppressLint("SuspiciousIndentation", "ResourceType")

@Composable
fun UsersListPage(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    val categories = listOf("ENT Specialist", "Orthopedic Specialist", "Gastroenterologist", "Dermatologist", "Neurologist", "Psychiatrist")
    val ages = listOf("<25", "<35", "<=45", ">=45")
    var _userList by remember { mutableStateOf<List<User>>(emptyList()) } //0 ///dummy list //0

    var selectedCategory by remember { mutableStateOf(categories[0]) }
    var selectedAge by remember { mutableStateOf(ages[0]) }
    var isDataLoaded by remember { mutableStateOf(false) } // Track whether data is loaded

//    fun fetchUserList() {
//        val firebaseRef = FirebaseDatabase.getInstance().getReference("users")
//        firebaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val userList = mutableListOf<User>()
//                for (userSnapshot in snapshot.children) {
//                    val user = userSnapshot.getValue(User::class.java)
//                    user?.let {
//                        userList.add(it)
//                    }
//                }
//                Log.d("UserList",userList.toString())
//                _userList.value = userList
//                isDataLoaded = true
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Handle error
//            }
//        })
//    }
    LaunchedEffect(true) {
        _userList=fetchUserList() //6
    }
//dummy
//0
    @Composable
    fun FilterRow(
        categories: List<String>,
        ages: List<String>

    ) {

        Row {
            // Category dropdown
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /* Dismiss dropdown on outside click */ }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(onClick = {
                        selectedCategory = category
                    }) {
                        Text(text = category)
                    }
                }
            }

            // Age dropdown
            DropdownMenu(
                expanded = false,
                onDismissRequest = { /* Dismiss dropdown on outside click */ }
            ) {
                ages.forEach { age ->
                    DropdownMenuItem(onClick = {
                        selectedAge = age
                    }) {
                        Text(text = age)
                    }
                }
            }
        }
    }


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
            ) {

                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.raw.user), contentDescription ="user" )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,

        ) { innerPadding ->
        FilterRow(categories, ages)
        Log.d("UserList1",_userList.toString())

        if (!isDataLoaded) {
            Log.d("UserList1",_userList.toString())

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            UserListContent(userList = _userList, paddingValues = innerPadding)
        }
    }}