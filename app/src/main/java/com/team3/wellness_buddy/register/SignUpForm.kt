package com.team3.wellness_buddy.register

import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.*

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll


import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import com.team3.wellness_buddy.usersList.getWindowToolBarHeight

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpForm(
    paddingValues: PaddingValues
) {


    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val genders = listOf("Male", "Female", "Others")
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var bio by remember {
        mutableStateOf("")
    }
    var street by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var zipCode by remember {
        mutableStateOf("")
    }
    var state by remember {
        mutableStateOf("")
    }
    var country by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .padding(paddingValues)
//            .padding(
//                top = getWindowStatusBarHeight(),
//                bottom = getWindowToolBarHeight() + 10.dp
//            )
            .padding(horizontal = 10.dp)
            .imePadding()
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )

    ) {

        Spacer(modifier = Modifier.height(10.dp))

        //personal Info
        IconText(
            modifier = Modifier,
            iconImage = R.raw.describe,
            iconText = "personal Info",
            text = "Personal Info"
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyTextField(
                modifier = Modifier.weight(1.5f),
                label = "First Name",
                value = firstName,
                onValueChange = { firstName = it },
                iconImage = R.raw.name,
                isIconAvailable = true
                )
            Spacer(modifier = Modifier.width(10.dp))
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Last Name",
                value = lastName,
                onValueChange = { lastName = it },

                )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Add more rows for gender, email, bio, address, checkboxes, etc.
        // Example:
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            DropDownTextField(
                modifier = Modifier
                    .weight(1.1f),
                label = "Gender",
                value = gender,
                menuItems = genders,
                onValueChange = { gender = it },
                iconImage = R.raw.gender,
            )
            Spacer(modifier = Modifier.width(10.dp))
            MyTextField(
                modifier = Modifier
                .weight(1f), label = "Age", value = age, onValueChange = { age = it },
                iconImage = R.raw.age,
                isIconAvailable = true)
        }
        Spacer(modifier = Modifier.height(16.dp))
        MyTextField(
            modifier = Modifier
            .fillMaxWidth(),
            label = "Email@example.com",
            value = email,
            onValueChange = { email = it },
            iconImage = R.raw.email,
            isIconAvailable = true)
        Spacer(modifier = Modifier.height(16.dp))
        MyTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(rememberScrollState()),
            label = "Describe Your Self",
            value = bio,
            onValueChange = { bio = it },
            )
        Spacer(modifier = Modifier.height(16.dp))

//Address
        IconText(
            modifier = Modifier,
            iconImage = R.raw.address,
            iconText = "address",
            text = "Address"
        )
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "City",
                value = city,
                onValueChange = { city = it },
                )
            Spacer(modifier = Modifier.width(16.dp))
            MyTextField(
                modifier = Modifier.weight(2f),
                label = "Street",
                value = street,
                onValueChange = { street = it })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Zip Code",
                value = zipCode,
                onValueChange = { zipCode = it })
            Spacer(modifier = Modifier.width(16.dp))
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "State",
                value = state,
                onValueChange = { state = it })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            MyTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Country",
                value = country,
                onValueChange = { country = it })

        }

        Spacer(modifier = Modifier.height(16.dp))
        // Add checkboxes
        // Example:
        var seekerChecked by remember {
            mutableStateOf(true)
        }




        var checkboxes by remember { mutableStateOf(listOf<CheckboxState>()) }
        var coachChecked by remember { mutableStateOf(false) }

        // Add checkboxes dynamically
        if (checkboxes.isEmpty()) {
            checkboxes = listOf(
                CheckboxState("Seeker"),
                CheckboxState("Coach")
            )
        }

        Row(modifier=Modifier.fillMaxWidth()) {
            checkboxes.forEachIndexed { index, checkboxState ->
                MyCheckBox(
                    text = checkboxState.text,
                    checked = checkboxState.checked,
                    onCheckedChange = { isChecked ->
                        // Update the state of the clicked checkbox
                        checkboxes = checkboxes.mapIndexed { i, state ->
                            if (index == i) CheckboxState(state.text, isChecked)
                            else CheckboxState(state.text, false)
                        }

                        // If "Coach" checkbox is checked, set coachChecked to true
                        if (checkboxState.text == "Coach") {
                            coachChecked = isChecked
                        }else if(checkboxState.text == "Seeker"){
                            if (isChecked) {
                                coachChecked = false
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Show the column if "Coach" checkbox is checked

        }

        var category by remember {
            mutableStateOf("")
        }
        var category_list= listOf("Psychiatrist","Dermatologist","Nutrient")

        var level by remember {
            mutableStateOf("")
        }
        var level_list= listOf("Expert","Student")

        if (coachChecked ) {

            Column(
                modifier = Modifier.padding(bottom = getWindowToolBarHeight()+80.dp)

            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Choose your Category: ")
                Spacer(modifier = Modifier.height(16.dp))
                DropDownTextField(
                    modifier = Modifier,
                    label = "Category",
                    value = category,
                    menuItems = category_list,
                    onValueChange = {category=it},
                    iconImage = R.raw.question
                )
                Spacer(modifier = Modifier.height(16.dp))
                DropDownTextField(
                    modifier = Modifier,
                    label = "Level",
                    value = level,
                    menuItems = level_list,
                    onValueChange = {level=it}
                )
                // Add more content here as needed
            }
        }

//        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(80.dp),
                    horizontalArrangement = Arrangement.Center

            ) {
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(0.5f)
                    .height(100.dp/2)
                    .width(100.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(Custom_Colors.Primary_bg)
                    ) {
                Text(text = "Submit",
                   color = Color.White)
            }

        }

    }



}

data class CheckboxState(val text: String, val checked: Boolean = false)

@Composable
fun MyCheckBox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text)
    }
}
