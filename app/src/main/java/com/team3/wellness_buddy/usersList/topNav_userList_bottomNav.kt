package com.team3.wellness_buddy.usersList


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team3.wellness_buddy.Values
import com.team3.wellness_buddy.getBottomToolBarHeight
import com.team3.wellness_buddy.getStatusBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors

import androidx.compose.foundation.Image


import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.getWindowStatusBarHeight

@Composable
fun getTopNavHeight(): Dp {
    return getWindowStatusBarHeight() + 55.dp
}



@Composable
fun getWindowToolBarHeight():Dp{
    val btmToolBarHeight= getBottomToolBarHeight(LocalContext.current)
    val btmToolBarHeightDp=with(LocalDensity.current){
        btmToolBarHeight.toDp()
    }
    return btmToolBarHeightDp
}

@Composable
fun getBottomToolHeight():Dp{
    return getWindowToolBarHeight()+100.dp
}


var usersList_height=0.dp

@Composable
fun NavigationBar(onGearIconClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(getTopNavHeight())
            .background(Custom_Colors.Primary_bg),

    ) {
        // Text field
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = getWindowStatusBarHeight() + 5.dp, start = 5.dp, end = 10.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = Values.app_name ,
                modifier = Modifier.padding(start = 16.dp),
                color = Color.White,
                fontSize = 15.sp
            )

            // Gear icon
            IconButton(onClick = { onGearIconClick() }) {
                Icon(
                    modifier = Modifier
                        .background(color = Color.White, RoundedCornerShape(20.dp))
                        .padding(5.dp),
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Settings",
                    tint = Custom_Colors.Primary_bg_lite
                )
            }
        }

    }
}

@SuppressLint("ResourceType")
@Composable
fun UserListElement(){

    val name="Satya Nandan"
    val description="Cardiologist"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .height(70.dp)
            .background(Color.White, RoundedCornerShape(20.dp))
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = CenterVertically
        ) {
            // Left: User profile picture
            Image(
                painter = painterResource(id = R.raw.wellness_buddy_bg_image), // Replace with your image resource
                contentDescription = "User profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            // Center: Name and description
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = name,
                    style = typography.subtitle1
                )
                Text(
                    text = description,
                    style = typography.caption,
                    textAlign = TextAlign.Start
                )
            }

            // Right: Favorite icon
            Image(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Favorite",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
//fun UserList(userList:List<User>)
fun UserList() {
    val scrollState= rememberScrollState()

    val screenHeight= LocalConfiguration.current.screenHeightDp.dp
    
    val height_req=screenHeight-(getTopNavHeight()+ getBottomToolHeight())
    usersList_height=height_req
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .height(height_req)
            .background(Color.LightGray)
            .verticalScroll(scrollState)
            .padding(bottom = getBottomToolHeight())

    ){
        Column {
            for(i in 0..20){

                UserListElement()
            }

        }


    }
}



@SuppressLint("ResourceType")
@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun BottomMenuBar(){
    var selected by remember{
        mutableStateOf(0)
    }

        // Floating icon
        Box(
            modifier = Modifier
                .width(80.dp)
                .background(
                    Custom_Colors.Primary_bg,
                    RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                )
                .padding()
                .height(getBottomToolHeight())
                

        ) {
            Icon(
                imageVector =Icons.Default.Home,
                contentDescription = "Add",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 20.dp)
                    .size(40.dp),
                tint = Color.White
            )

//            Image(
//                painter = painterResource(id = R.raw.wellness_buddy_bg_image), // Replace with your image resource
//                contentDescription = "User profile picture",
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//            )
        }


}

