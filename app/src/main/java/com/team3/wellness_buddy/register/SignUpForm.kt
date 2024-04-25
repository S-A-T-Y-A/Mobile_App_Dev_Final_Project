package com.team3.wellness_buddy.register

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.getWindowToolBarHeight
import com.team3.wellness_buddy.ui.theme.Custom_Colors

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.security.MessageDigest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpForm(
    paddingValues: PaddingValues,

) {

    lateinit var firebaseRef : DatabaseReference
    firebaseRef = FirebaseDatabase.getInstance().getReference("users")

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val genders = listOf("Male", "Female", "Others")
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
    val dialogMessage = remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyTextField(
                modifier = Modifier.weight(1.5f),
                label = "Password",
                value = password,
                onValueChange = { password = it },
            )
            Spacer(modifier = Modifier.width(10.dp))
            MyTextField(
                modifier = Modifier.weight(1f),
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
            )
        }
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


        fun validateInput(): Boolean {
            return when {
                firstName.isEmpty() -> {
                    dialogMessage.value = "Please enter First Name"
                    openDialog.value = true
                    false
                }
                lastName.isEmpty() -> {
                    dialogMessage.value = "Please enter Last Name"
                    openDialog.value = true
                    false
                }
                gender.isEmpty() -> {
                    dialogMessage.value = "Please select Gender"
                    openDialog.value = true
                    false
                }
                password.isEmpty() -> {
                    dialogMessage.value = "Please enter Password"
                    openDialog.value = true
                    false
                }
                confirmPassword.isEmpty() -> {
                    dialogMessage.value = "Please enter Confirm Password"
                    openDialog.value = true
                    false
                }
                password != confirmPassword -> {
                    dialogMessage.value = "Passwords do not match"
                    openDialog.value = true
                    false
                }
                age.isEmpty() -> {
                    dialogMessage.value = "Please enter Age"
                    openDialog.value = true
                    false
                }
                email.isEmpty() -> {
                    dialogMessage.value = "Please enter Email"
                    openDialog.value = true
                    false
                }
                bio.isEmpty() -> {
                    dialogMessage.value = "Please enter Bio"
                    openDialog.value = true
                    false
                }
                street.isEmpty() -> {
                    dialogMessage.value = "Please enter Street"
                    openDialog.value = true
                    false
                }
                city.isEmpty() -> {
                    dialogMessage.value = "Please enter City"
                    openDialog.value = true
                    false
                }
                zipCode.isEmpty() -> {
                    dialogMessage.value = "Please enter Zip Code"
                    openDialog.value = true
                    false
                }
                state.isEmpty() -> {
                    dialogMessage.value = "Please enter State"
                    openDialog.value = true
                    false
                }
                country.isEmpty() -> {
                    dialogMessage.value = "Please enter Country"
                    openDialog.value = true
                    false
                }
                else -> true
            }
        }

        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(80.dp),
                    horizontalArrangement = Arrangement.Center

            ) {
            Button(onClick = {
                if (validateInput()) {
                    val hashedPassword = hashString(password)
                    Log.d("HashedPassword",hashedPassword)
                    val user = User(
                        firstName,
                        lastName,
                        gender,
                        age,
                        email,
                        hashedPassword,
                        bio,
                        street,
                        city,
                        zipCode,
                        state,
                        country,
                        if (seekerChecked) "Seeker" else if (coachChecked) "Coach" else ""
                    )
                    // Print user details before storing to Firebase
                    Log.d("SignUpForm", user.toString())
                    var userId = firebaseRef.push().key!!
                    firebaseRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent (
                        object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    // Email already exists
                                    dialogMessage.value = "Email already exists!"
                                    openDialog.value = true
                                } else {
                                    // Email does not exist, save the new user data
                                    firebaseRef.push().setValue(user).addOnSuccessListener {
                                        dialogMessage.value = "Data saved successfully!"
                                        openDialog.value = true
                                    }
                                        .addOnFailureListener {
                                            dialogMessage.value = "Error: ${it.message}"
                                            openDialog.value = true
                                        }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                dialogMessage.value = "Error: ${error.message}"
                                openDialog.value = true
                            }
                        }
                    )
                }

            },
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
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Validation Error") },
                text = { Text(text = dialogMessage.value) },
                confirmButton = {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text("OK")
                    }
                }
            )
        }

    }
}

private fun hashString(input: String): String {
    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = MessageDigest
        .getInstance("SHA-256")
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt() and 0xff
        result.append(HEX_CHARS[i shr 4])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}

data class User(
    val firstName: String,
    val lastName: String,
    val gender: String,
    val age: String,
    val email: String,
    val hashedPassword: String,
    val bio: String,
    val street: String,
    val city: String,
    val zipCode: String,
    val state: String,
    val country: String,
    val role: String
)

data class CheckboxState(val text: String, val checked: Boolean = false)

@Composable
fun MyCheckBox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text)
    }
}
