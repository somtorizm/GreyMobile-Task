package com.cloudchef.greymobilegithubtask.presentation.user_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.cloudchef.greymobilegithubtask.R
import com.cloudchef.greymobilegithubtask.presentation.search_repository.SearchEmptyStateView
import com.cloudchef.greymobilegithubtask.presentation.search_user.RepoCard

@Composable
fun UserProfileScreen(navController: NavController,
                      viewModel: GithubUserViewModel = hiltViewModel()) {
    val state = viewModel.state
    val scrollState = rememberLazyListState()


    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "back",
                tint = Color.Gray,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )

            Text(
                text = "Users",
                fontSize = 18.sp,
                modifier = Modifier
            )
        }
        
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            
            Image(
                painter = rememberAsyncImagePainter(state.user?.avatarUrl ?: ""),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = state.user?.name ?: "", style = MaterialTheme.typography.labelMedium, fontSize = 18.sp)
                Text(text = state.user?.company ?: "", style = MaterialTheme.typography.labelSmall,  fontSize = 15.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.user?.bio ?: "",
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = painterResource(id = R.drawable.location), contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = state.user?.location ?: "", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.width(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.link), contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = state.user?.url ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surface,
                    fontWeight = FontWeight.Bold, )
            }
        }

        
        Spacer(modifier = Modifier.height(16.dp))

        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Icon(painter = painterResource(id = R.drawable.people), contentDescription = null, tint = Color.Gray)
            Text(text = (state.user?.followers ?: 0).toString() + " Followers", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "${state.user?.following} following", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(text = "Repositories", style = MaterialTheme.typography.bodySmall)

            Text(
                text = state.user?.publicRepos.toString(),
                color = Color.Gray,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        CustomLine()

       when {
           state.isLoading -> {
               SearchEmptyStateView("Searching for Repositories", R.drawable.no_content)
           }

           state.error != null -> {
               SearchEmptyStateView("An error occurred: ${state.error}", R.drawable.no_content)
           }

           state.usersRepo?.isEmpty() == true -> {
               SearchEmptyStateView("This user doesn’t have repositories yet, come back later :-)", R.drawable.no_content)
           }
           state.usersRepo == null -> {
               SearchEmptyStateView("This user  doesn’t have repositories yet, come back later :-)", R.drawable.no_content)
           }
           else -> {
               Spacer(modifier = Modifier.height(40.dp))

               LazyColumn(state = scrollState) {
                   items(state.usersRepo, key = { it.id }) { repo ->
                       RepoCard(
                           title = repo.name,
                           subtitle = repo.owner.login,
                           url = repo.owner.avatarUrl,
                           stars = repo.stargazersCount,
                           language = repo.language ?: "",
                           description = repo.description ?: "",
                           tags = repo.topics.take(4)
                       ) {
                       }
                   }
               }
           }
       }
    }
}

@Composable
fun CustomLine() {
    val color = if(isSystemInDarkTheme()) Color.White else Color.Gray
    val color1 = Color.Gray

    Canvas(modifier = Modifier.fillMaxWidth()) {
        val startX = 0f
        val endX = size.width
        val startY = size.height / 3
        val endY = size.height / 3

        val initialThickness = 5f
        val finalThickness = 3f


        val brush = Brush.horizontalGradient(
            colors = listOf(
                color.copy(alpha = 1f),
                color1.copy(alpha = 0f)
            )
        )

        drawLine(
            brush = brush,
            start = Offset(startX, startY),
            end = Offset(startX + (endX / 3), endY),
            strokeWidth = initialThickness
        )

        drawLine(
            color = color1,
            start = Offset(startX + (endX / 3), startY),
            end = Offset(endX, endY),
            strokeWidth = finalThickness
        )
    }
}