package com.team3.wellness_buddy.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.team3.wellness_buddy.UserPreferences
import com.team3.wellness_buddy.helpers.getWindowStatusBarHeight
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SignUpPage(navController: NavController){
    val context= LocalContext.current
    val view = LocalView.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .padding(
                top = getWindowStatusBarHeight(),
                bottom = getWindowToolBarHeight() + 10.dp
            ),
        topBar = {
            TopAppBar(
                {
                    Box(modifier = Modifier
                        .background(Color.Transparent)){
                        Text(text = "Register")
                    }
                },
                backgroundColor = Custom_Colors.Primary_bg,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch {
                        navController.popBackStack()
                    } }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Menu", tint = Color.White)
                    }
                },

            )
        }
    ) {
        innerPadding->
        SignUpForm(
            paddingValues=innerPadding,
            navController,
            onSaveUserInfo = { firstName, lastName, email, role ->
                UserPreferences.saveUserInfo(context, firstName, lastName, email, role)
            }
        )
    }
}

