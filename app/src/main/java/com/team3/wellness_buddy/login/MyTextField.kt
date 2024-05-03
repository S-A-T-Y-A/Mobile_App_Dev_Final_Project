package com.team3.wellness_buddy.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team3.wellness_buddy.ui.theme.Custom_Colors
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.helpers.IconText

@Composable
fun MyTextField(
    modifier: Modifier=Modifier,
    lable:String,
    value:String,
    onValueChange:(String)->Unit,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    readOnly:Boolean=false,
    height: Dp =45.dp,
    iconImage:Int

){

    Column(modifier=Modifier
        ){
        IconText(modifier = Modifier, iconImage = iconImage, iconText =lable , text = lable)
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value =value ,
            onValueChange =onValueChange,
            keyboardOptions = keyboardOptions ,
            keyboardActions = keyboardActions,
            readOnly = readOnly,

            visualTransformation = if (keyboardOptions.keyboardType == KeyboardType.Password) {
                PasswordVisualTransformation()
            } else {
                // No visual transformation for non-password fields
                VisualTransformation.None
            },

            ){
            Row(modifier= Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .height(height)
                .background(Custom_Colors.textField_color),
                verticalAlignment = Alignment.CenterVertically){
                Box(
                    modifier=Modifier
                        .padding(horizontal = 15.dp),
                    contentAlignment = Alignment.CenterStart
                ){
                    it.invoke()
                }
            }
        }
        
    }
}