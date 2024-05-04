package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box



import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DrawerValue
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
import androidx.compose.material.rememberDrawerState

import androidx.compose.runtime.Composable
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
import androidx.lifecycle.MutableLiveData
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

val finalUserList = MutableLiveData<List<User>>()

@SuppressLint("SuspiciousIndentation", "ResourceType")

@Composable
fun UsersListPage(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    val categories = listOf("ENT Specialist", "Orthopedic Specialist", "Gastroenterologist", "Dermatologist", "Neurologist", "Psychiatrist")
    val ages = listOf("<25", "<35", "<=45", ">=45")

    val userList = mutableListOf<User>()
    var selectedCategory by remember { mutableStateOf(categories[0]) }
    var selectedAge by remember { mutableStateOf(ages[0]) }
    var isDataLoaded by remember { mutableStateOf(false) } // Track whether data is loaded

    var isMenuClicked by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    var firebaseRef = FirebaseDatabase.getInstance().getReference("users")
    firebaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

            for (userSnapshot in snapshot.children) {
                val user = userSnapshot.getValue(User::class.java)
                    user?.let {
                        val selectedCategories = UserPreferences.getSelectedCategories(context);
                        val role = UserPreferences.getUserRole(context);
                        Log.d("UserList 12", role.toString())
                        val isCategoryExist =
                            selectedCategories?.contains(it.category.toString()) == true;
                        Log.d("Log Seeker",it.role)
                        if(role == "Seeker" && it.role != "Seeker"){
                            if(selectedCategories != null && selectedCategories.isNotEmpty() ){
                                if(isCategoryExist){
                                    userList.add(it)
                                }
                                else{

                                }
                            }
                            else{
                                userList.add(it)
                            }

                        }
                        else if (role == "Coach" && it.role != "Coach"){
                            userList.add(it)
                        }
                        else{

                        }

                    }
                }
                isDataLoaded = true
                finalUserList.value = userList
                Log.d("UserList", finalUserList.value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
    })


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
                    IconButton(onClick = {
                        isMenuClicked=!isMenuClicked
                        Log.d("IsMenuClicked","$isMenuClicked")
                    }) {
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
            val userEmail=UserPreferences.getEmail(context)
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
                if (userEmail != null) {
                    ProfilePicture(email = userEmail, sizeDp = 50.dp.value.toInt())
                }
//                Image(
//                    modifier = Modifier.fillMaxSize(),
//                    painter = painterResource(id = R.raw.user), contentDescription ="user" )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,




        )
    { innerPadding ->


        Log.d("User email",""+UserPreferences.getEmail(context))

        if(isMenuClicked){

            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
                contentAlignment = Alignment.TopEnd

            ) {
                DropdownMenu(expanded = true, onDismissRequest = { isMenuClicked = false },
                    modifier=Modifier.fillMaxWidth()) {
                    DropdownMenuItem(onClick = {
                        navController.navigate("about")
                    }, modifier = Modifier) {
                        Text(text = "About")
                    }
                    DropdownMenuItem(onClick = {

                    }) {
                        Text(text = "Profile")
                    }
                    DropdownMenuItem(onClick = {
                        UserPreferences.saveSelectedCategories(emptySet(), context)
                        UserPreferences.saveUserInfo(context,"","","","")
                        navController.navigate("login")
                    }) {
                        Text(text = "Logout")
                    }

                }

            }
        }

        FilterRow(categories, ages)
        Log.d("UserList1",finalUserList.toString())

        if (!isDataLoaded) {
            Log.d("UserList1",finalUserList.toString())

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            finalUserList.value?.let { userList ->
                UserListContent(userList = userList, paddingValues = innerPadding, navController)
            }
        }
    }
}