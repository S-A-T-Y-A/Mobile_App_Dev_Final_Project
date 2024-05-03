package com.team3.wellness_buddy.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.team3.wellness_buddy.helpers.MyCustomIcon
import com.team3.wellness_buddy.helpers.loadMyIcon
import com.team3.wellness_buddy.ui.theme.Custom_Colors


@Composable
fun DropDownTextField(
    modifier: Modifier,
    label:String,
    value:String,
    iconImage: Int? =null,
    iconSize:Int=24,
    menuItems:List<String>,
    onValueChange:(String)->Unit,
    keyboardOptions: KeyboardOptions=KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions=KeyboardActions(onDone = null),
    readOnly:Boolean=false,
    height: Dp =60.dp,
    textFieldColor:Color= Custom_Colors.textFieldColor,
    textColor:Color=Custom_Colors.Primary_bg,
    fontSize: Float = 10f,
    maxLines:Int=1
) {

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }


    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier=modifier
    ) {
        val myIconBitmap= iconImage?.let { loadMyIcon(iconImage = it, altText = label+"_icon") }
        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .height(height)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = textFieldColor,
                unfocusedBorderColor = textFieldColor,
                cursorColor = textFieldColor,
                focusedLabelColor = textFieldColor,
                textColor = textColor
            ),
            label = {Text(label)},
            textStyle = TextStyle(fontSize = fontSize.sp),
            leadingIcon = {
                if (myIconBitmap != null) {
                    MyCustomIcon(iconImage = myIconBitmap, iconSize = (height/3).value.toInt())
                }
            },
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            },
            maxLines=maxLines
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            menuItems.forEach { label ->
                DropdownMenuItem(onClick = {
                    onValueChange(label)
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}