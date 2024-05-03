package com.team3.wellness_buddy.categoryexplorer

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team3.wellness_buddy.R
import com.team3.wellness_buddy.login.Custom_Button
import com.team3.wellness_buddy.ui.theme.Custom_Colors



@SuppressLint("ResourceType")
@Composable
fun CategorySelectionPage() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Custom_Colors.Primary_bg_lite),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.raw.logo_mobile_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = 0.13f)
        )

        CategoryList()

    }
}

@Composable
fun CategoryList(

) {
    val categoryListImages = listOf(
        R.raw.ent,
        R.raw.orthopedic,
        R.raw.gastroentrologist,
        R.raw.dermatoligist,
        R.raw.neurologist,
        R.raw.psychiatrist
    )
    val categoryList= listOf(
        "ENT Specialist",
        "Orthopedic Specialist",
        "Gastroenterologist",
        "Dermatologist",
        "Neurologist",
        "Psychiatrist")

    val selectedCategories :MutableSet<String> = mutableSetOf();
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var index = 0
        for (row in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0..1) {
                    if (index < categoryListImages.size) {
                        val cat_name=categoryList[index]
                        ClickableImageCircle(
                            imageResId = categoryListImages[index],
                            text = categoryList[index],
                            selectedCategories = selectedCategories
                        )
                        index += 1
                        println(index)
                    }
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
        }

        Button(
            onClick = {
                println("Selected categories")
                selectedCategories.forEach { category ->
                    println(category)
                }
            },
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(end = 8.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text("Go to List", color = Custom_Colors.Primary_bg)
        }


    }
}

@Composable
fun ClickableImageCircle(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit={},
    imageResId: Int,
    text: String,
    imageSize: Dp = 140.dp,
    selectedCategories: MutableSet<String>,
    maxSelection: Int = 2 // Maximum number of selections allowed
) {
    var isSelected by remember { mutableStateOf(selectedCategories.contains(text)) }


    Box(
        modifier = modifier
            .size(imageSize)
            .clickable {
                isSelected = !isSelected // Toggle selection state
//isSelected &&
                isSelected = if (isSelected && selectedCategories.size < maxSelection) {
                    selectedCategories.add(text)
                    selectedCategories.contains(text)
                } else {
                    selectedCategories.remove(text)
                    selectedCategories.contains(text)
                    // Remove category from selected list
                }
            },
        contentAlignment = Alignment.BottomCenter // Align the content to the bottom
    ) {
        val textColor = if (isSelected) Color.White else Color.Gray // Change text color based on selection state

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier

                .padding(bottom = 30.dp)
                .size(imageSize / 2),
        )
        Text(
            text = text,
            color = textColor,

        )
    }
}




@Preview(showBackground = true)
@Composable
fun Show(){
    CategorySelectionPage ()
}
