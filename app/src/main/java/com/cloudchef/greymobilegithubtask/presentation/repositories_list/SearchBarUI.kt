package com.cloudchef.greymobilegithubtask.presentation.repositories_list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.ui.theme.ButtonColor
import com.cloudchef.greymobilegithubtask.ui.theme.GreyMobileGithubTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(querySearch: (query: String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    GreyMobileGithubTaskTheme {

        Row(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .height(60.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                placeholder = { Text("Search for users...", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                        tint = MaterialTheme.colorScheme.surface
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .border(width = 0.dp, color = Color.Transparent),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                   containerColor = Color.Transparent
                )
            )

            Button(
                onClick = {

                     querySearch(searchQuery.text)
                },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(
                     containerColor = ButtonColor,
                     contentColor = Color.White,
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    "Search",
                    fontSize = 16.sp,
                )
            }
        }
    }
}