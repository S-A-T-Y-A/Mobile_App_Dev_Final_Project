package com.team3.wellness_buddy.register

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.helpers.MyCustomIcon
import com.team3.wellness_buddy.helpers.loadMyIcon
import com.team3.wellness_buddy.ui.theme.Custom_Colors


@Composable
fun MyTextField(
    modifier: Modifier,
    label:String,
    value:String,
    iconImage: Int? =null,
    iconSize:Int=24,
    isIconAvailable:Boolean=false,
    onValueChange:(String)->Unit ,
    nextFocus: (() -> Unit)? = null, // Function to focus on next field
    keyboardOptions: KeyboardOptions=KeyboardOptions(
                        imeAction = if (nextFocus != null) ImeAction.Next else ImeAction.Done
                    ),
    readOnly:Boolean=false,
    height: Dp =60.dp,
    textFieldColor:Color=Custom_Colors.textFieldColor,
    textColor:Color=Custom_Colors.Primary_bg,
    maxLines:Int=1
             ){
    val myIconBitmap= iconImage?.let { loadMyIcon(iconImage = it, altText = label+"_icon") }


    if (isIconAvailable){
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {Text(label)},
            modifier = modifier
                .height(height),
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { nextFocus?.invoke() }, // Move focus to the next field
                onNext = { nextFocus?.invoke() } // Move focus to the next field
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = textFieldColor,
                unfocusedBorderColor = textFieldColor,
                cursorColor = textFieldColor,
                focusedLabelColor = textFieldColor,
                textColor = textColor
            ),

            leadingIcon = {
                if (myIconBitmap != null) {
                    MyCustomIcon(iconImage = myIconBitmap, iconSize = (height / 3).value.toInt())
                }
            },
            maxLines = maxLines,

        )
    }else{
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {Text(label)},
            modifier = modifier
                .height(height),
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { nextFocus?.invoke() }, // Move focus to the next field
                onNext = { nextFocus?.invoke() } // Move focus to the next field
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = textFieldColor,
                unfocusedBorderColor = textFieldColor,
                cursorColor = textFieldColor,
                focusedLabelColor = textFieldColor,
                textColor = textColor
            ),
            maxLines = maxLines

            )
    }


}

