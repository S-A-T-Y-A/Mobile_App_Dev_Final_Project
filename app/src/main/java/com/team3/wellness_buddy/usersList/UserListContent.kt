package com.team3.wellness_buddy.usersList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight

@Composable
//fun UserList(userList:List<User>)
fun UserListContent(paddingValues: PaddingValues) {
    val scrollState= rememberScrollState()
    Column(
            modifier = Modifier
                .padding(paddingValues)
                .imePadding()
                .fillMaxSize()
                .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally


        ) {
            val name="Satya"
            val description="Psychiatrist"
        Spacer(modifier = Modifier.height(10.dp))
            for(i in 0..10){

                UserListElement(name,description)
                Spacer(modifier = Modifier.height(10.dp))
            }
        Spacer(modifier = Modifier.height(65.dp))

        }

}