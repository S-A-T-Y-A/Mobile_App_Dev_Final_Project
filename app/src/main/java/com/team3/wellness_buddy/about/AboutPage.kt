package com.team3.wellness_buddy.about

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.getWindowStatusBarHeight
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@Composable
fun AboutContent(paddingValues: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Custom_Colors.Primary_bg_lite)
        .padding(horizontal = 20.dp)
        .padding(top = 50.dp, bottom = 30.dp)){

        Image(
            painter = painterResource(id = R.raw.logo_mobile_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.3f)
        )
        
        Text(text = "Welcome to Wellness Buddy – your go-to companion for all things wellness! We get that taking care of yourself can be confusing sometimes. That's why we're here to help. With Wellness Buddy, you can chat with experts about anything from feeling stressed to eating better, and get instant advice that fits you perfectly.\n" +
                "\n" +
                "Our goal is to make healthy living easy and accessible for everyone. So, whether you need tips for sleeping better or just want to feel happier, our team is here to support you.\n" +
                "\n" +
                "Join us today and let's make your wellness journey awesome, together!\n" +
                "\n",
            color = Color.White, modifier = Modifier.align(Alignment.TopCenter),textAlign = TextAlign.Justify)
        

        
    }
}

@Composable
fun AboutPage(navController: NavHostController){
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
                        Text(text = "About Us")
                    }
                },
                backgroundColor = Custom_Colors.Primary_bg,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },

                )
        }
    ) {paddingValues->
        AboutContent(paddingValues)
    }
}